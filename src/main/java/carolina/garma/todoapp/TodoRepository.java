package carolina.garma.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static carolina.garma.todoapp.TodoPage.getPage;

@Repository
public class TodoRepository implements TodoRepositoryInterface {
    private static ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(
                    "XYZ",
                    //"UNDONE",
                    Todo.done_enum.UNDONE,
                    //"HIGH",
                    Todo.priority_level.HIGH,
                    LocalDateTime.of(2001, 2, 14,0,0,0),
                    null,
                    LocalDateTime.of(2003, 2, 14,0,0,0)
            ),
            new Todo(
                    //1L,
                    "ABC",
                    //"DONE",
                    Todo.done_enum.DONE,
                    Todo.priority_level.LOW,
                    //"LOW",
                    LocalDateTime.of(2000, 2, 14,0,0,0),
                    LocalDateTime.of(2000, 3, 1,9,16,0),
                    LocalDateTime.of(2001, 2, 14,0,0,0)
            ),
            new Todo(
                    //1L,
                    "XYZ",
                    //"DONE",
                    Todo.done_enum.DONE,
                    Todo.priority_level.HIGH,
                    //"HIGH",
                    LocalDateTime.of(2000, 2, 14,0,0,0),
                    LocalDateTime.of(2000, 2, 14,5,12,0),
                    LocalDateTime.of(2001, 2, 14,0,0,0)
            )
    )
    );

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    @Override
    public ResponseEntity<Object> addNewTodo(Todo todo) {
        LocalDateTime creation_date = LocalDateTime.now();
        todo.setCreation_date(creation_date);
        Map<String, Object> validation = TodoValidation.validateTodo(todo, todos);
        if (validation.isEmpty()) {
            todo.setAtomicId();
            todos.add(todo);
            return TodoResponseHandler.generateResponse("Todo created", HttpStatus.CREATED, todo, null);
        } else {
            return TodoResponseHandler.generateResponse("Invalid Todo", HttpStatus.BAD_REQUEST, todo, validation);
        }
    }

    @Override
    public ResponseEntity<Object> updateTodo(int id, Todo todo) {
        // Logic for dealing with deleted ids
        Todo to_update = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);

        if (to_update == null) {
            //throw new TodoNotFoundException(id); // Raise Not Found Exception
            return TodoResponseHandler.generateResponse("Todo with id " + id + " not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            Map<String, Object> validation = TodoValidation.validateTodo(todo, todos);
            if (validation.isEmpty()) {
                if (todo.getContent() != null) {
                    to_update.setContent(todo.getContent());
                }
                if (todo.getPriority() != null) {
                    to_update.setPriority(todo.getPriority());
                }
                /*if (todo.getDue_date() != null) {
                    to_update.setDue_date(todo.getDue_date());
                }*/
                to_update.setDue_date(todo.getDue_date());
                return TodoResponseHandler.generateResponse("Todo updated", HttpStatus.OK, to_update, null);
            } else {
                return TodoResponseHandler.generateResponse("Invalid Todo", HttpStatus.BAD_REQUEST, todo, validation);
            }
        }
    }

    @Override
    public ResponseEntity<Object> updateDone(int id) {
        // Logic for dealing with deleted ids
        Todo to_done = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_done == null) {
            //throw new TodoNotFoundException(id); // Raise Not Found Exception
            return TodoResponseHandler.generateResponse("Todo not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            LocalDateTime done_date = LocalDateTime.now();
            to_done.setDone_date(done_date);
            to_done.setFlag(Todo.done_enum.DONE);
            return TodoResponseHandler.generateResponse("Todo updated", HttpStatus.OK, to_done, null);
        }
    }

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
    @Override
    public ResponseEntity<Object> deleteTodo(int id) {
        // Logic for dealing with deleted ids
        Todo to_delete = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_delete == null) {
            //throw new TodoNotFoundException(id); // Raise Not Found Exception
            return TodoResponseHandler.generateResponse("Todo not found", HttpStatus.NOT_FOUND, null, null);
        } else {
            todos.remove(to_delete);
            return TodoResponseHandler.generateResponse("Todo removed", HttpStatus.OK, to_delete, null);
        }
    }
    @Override
    public TodoPage<Todo> getPages(List<Todo> todos,int page_number) {
        TodoPage<Todo> page = getPage(todos, page_number);
        return page;
    }


}
