package carolina.garma.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static carolina.garma.todoapp.TodoPage.getPage;

@Repository
public class TodoRepository implements TodoRepositoryInterface {
    /*
    Todo repository component which is in charge of providing the
    CRUD data operations, this component implements List Java Collections
    methods in order to create, add, delete and edit a todo object inside a list
    */

    // Initialization of my main todos list with some items in it
    private static ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(
                    "first todo created from backend",
                    Todo.done_enum.UNDONE,
                    Todo.priority_level.HIGH,
                    LocalDateTime.of(2022, 11, 14,0,0,0),
                    null,
                    LocalDateTime.of(2022, 12, 31,0,0,0)
            ),
            new Todo(
                    "wash my car",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 28,9,16,0),
                    LocalDateTime.of(2022, 11, 28,12,0,0)
            ),
            new Todo(
                    "prepare dinner",
                    Todo.done_enum.DONE,
                    Todo.priority_level.HIGH,
                    LocalDateTime.of(2022, 11, 10,0,0,0),
                    LocalDateTime.of(2022, 11, 10,5,12,0),
                    LocalDateTime.of(2022, 11, 28,12,0,0)
            ),
            new Todo(
                    "do Homework",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 28,9,16,0),
                    LocalDateTime.of(2022, 11, 28,12,0,0)
            ),
            new Todo(
                    "write essay",
                    Todo.done_enum.UNDONE,
                    Todo.priority_level.MEDIUM,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    null,
                    null
            ),
            new Todo(
                    "A TODO WITH UPPERCASE",
                    Todo.done_enum.DONE,
                    Todo.priority_level.MEDIUM,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 30,9,16,0),
                    null
            ),
            new Todo(
                    "a todo with lowecase",
                    Todo.done_enum.UNDONE,
                    Todo.priority_level.HIGH,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    null,
                    LocalDateTime.of(2022, 11, 26,12,0,0)
            ),
            new Todo(
                    "Clean my room",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 28,9,16,0),
                    LocalDateTime.of(2022, 11, 28,12,0,0)
            ),
            new Todo(
                    "Second todo from backend",
                    Todo.done_enum.UNDONE,
                    Todo.priority_level.MEDIUM,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    null,
                    null
            ),
            new Todo(
                    "Watch a movie",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 2,9,16,0),
                    null
            ),
            new Todo(
                    "read a book",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 28,9,16,0),
                    LocalDateTime.of(2022, 10, 2,12,0,0)
            ),
            new Todo(
                    "clean my keyboard",
                    Todo.done_enum.DONE,
                    Todo.priority_level.HIGH,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 28,9,16,0),
                    LocalDateTime.of(2022, 11, 28,12,0,0)
            ),
            new Todo(
                    "Feed my dog",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 11, 12,9,16,0),
                    LocalDateTime.of(2022, 11, 20,12,0,0)
            ),
            new Todo(
                    "feed my cat",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    LocalDateTime.of(2022, 10, 1,0,0,0),
                    LocalDateTime.of(2022, 10, 5,6,16,0),
                    LocalDateTime.of(2022, 11, 1,18,0,0)
            )

        )
    );

    // Method that return the current todos list
    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    // Method that attempts to ADD a new todo to the todos list depending on validation results
    // returns a Response Entity of 200 if success, 400 otherwise
    @Override
    public ResponseEntity<Object> addNewTodo(Todo todo) {
        Map<String, Object> validation = TodoValidation.validateTodo(todo, todos);
        if (validation.isEmpty()) { // If there are no errors, the validation map is empty
            LocalDateTime creation_date = LocalDateTime.now();
            todo.setCreation_date(creation_date);
            todo.setAtomicId();
            todos.add(todo); // add operation to list
            return TodoResponseHandler.generateResponse("Todo created", HttpStatus.CREATED, todo, null);
        } else {
            return TodoResponseHandler.generateResponse("Invalid Todo", HttpStatus.BAD_REQUEST, todo, validation);
        }
    }

    // Method that attempts to EDIT a todo from the todos list depending on validation results
    // returns a Response Entity of 200 if success, 400 if it was a bad request
    // and 404 if the provided ID was not found
    @Override
    public ResponseEntity<Object> updateTodo(int id, Todo todo) {
        // Logic for dealing with deleted ids
        Todo to_update = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);

        if (to_update == null) {
            // Raise Not Found Exception
            return TodoResponseHandler.generateResponse("Todo with id " + id + " not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            Map<String, Object> validation = TodoValidation.validateEditedTodo(todo, todos, to_update);
            if (validation.isEmpty()) {
                if (todo.getContent() != null) {
                    to_update.setContent(todo.getContent());
                }
                if (todo.getPriority() != null) {
                    to_update.setPriority(todo.getPriority());
                }
                to_update.setDue_date(todo.getDue_date());
                return TodoResponseHandler.generateResponse("Todo updated", HttpStatus.OK, to_update, null);
            } else {
                return TodoResponseHandler.generateResponse("Invalid Todo", HttpStatus.BAD_REQUEST, todo, validation);
            }
        }
    }

    // Method that sets the status flag as DONE and sets a done date when validated correctly
    // Returns a 200 status code when success, 404 otherwise
    // If the todo was already done, no error is thrown
    @Override
    public ResponseEntity<Object> updateDone(int id) {
        // Logic for dealing with non-existing ids
        Todo to_done = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_done == null) {
            // Raise Not Found Exception
            return TodoResponseHandler.generateResponse("Todo not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            LocalDateTime done_date = LocalDateTime.now();
            to_done.setDone_date(done_date);
            to_done.setFlag(Todo.done_enum.DONE);
            return TodoResponseHandler.generateResponse("Todo updated", HttpStatus.OK, to_done, null);
        }
    }

    // Method that sets the status flag as UNDONE and sets the done date as null when validated correctly
    // Returns a 200 status code when success, 404 otherwise
    // If the todo was already undone, no error is thrown
    @Override
    public ResponseEntity<Object> updateUndone(int id) {
        // Logic for dealing with deleted ids
        Todo to_undone = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_undone == null) {
            //throw new TodoNotFoundException(id); // Raise Not Found Exception
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo with id " + id + " not found");
            return TodoResponseHandler.generateResponse("Todo not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            to_undone.setDone_date(null);
            to_undone.setFlag(Todo.done_enum.UNDONE);
            return TodoResponseHandler.generateResponse("Todo updated", HttpStatus.OK, to_undone, null);
            //return ResponseEntity.status(HttpStatus.OK).body("Todo with id " + id + " updated");
        }
    }

    // Method that attempts to DELETE an existing todo from the todos list when a valid id is provided
    // It returns a 200 status code when success, 404 otherwise
    @Override
    public ResponseEntity<Object> deleteTodo(int id) {
        // Logic for dealing with non-existing ids
        Todo to_delete = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_delete == null) {
            // Raise Not Found Exception
            return TodoResponseHandler.generateResponse("Todo not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            todos.remove(to_delete);
            return TodoResponseHandler.generateResponse("Todo removed", HttpStatus.OK, to_delete, null);
        }
    }

    // Method that returns the todo page view requested
    @Override
    public TodoPage<Todo> getPages(List<Todo> todos,int page_number) {
        TodoPage<Todo> page = getPage(todos, page_number);
        return page;
    }


}
