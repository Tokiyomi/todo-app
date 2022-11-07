package carolina.garma.todoapp;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TodoRepository implements TodoRepositoryInterface {
    private static ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(
                    "XYZ",
                    "Undone",
                    "Z",
                    LocalDate.of(2001, 2, 14),
                    LocalDate.of(2002, 2, 14),
                    LocalDate.of(2003, 2, 14)
            ),
            new Todo(
                    //1L,
                    "ABC",
                    "Done",
                    "Z",
                    LocalDate.of(2003, 2, 14),
                    LocalDate.of(2002, 2, 14),
                    LocalDate.of(2001, 2, 14)
            ),
            new Todo(
                    //1L,
                    "XYZ",
                    "Done",
                    "A",
                    LocalDate.of(2003, 2, 14),
                    LocalDate.of(2002, 2, 14),
                    LocalDate.of(2001, 2, 14)
            )
    )
    );

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    @Override
    public void addNewTodo(Todo todo) {
        todo.setAtomicId();
        LocalDate creation_date = LocalDate.now();
        todo.setCreation_date(creation_date);
        todos.add(todo);
    }
    @Override
    public void updateTodo(int id, Todo todo) {
        // Logic for dealing with deleted ids
        Todo to_update = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);

        if (to_update==null) {
            throw new TodoNotFoundException(id); // Raise Not Found Exception
        } else {
            if (todo.getContent()!=null) {to_update.setContent(todo.getContent());}
            if (todo.getPriority()!=null) {to_update.setPriority(todo.getPriority());}
            if (todo.getDue_date()!=null) {to_update.setDue_date(todo.getDue_date());}
        }
    }
    @Override
    public void updateDone(int id) {
        // Logic for dealing with deleted ids
        Todo to_done = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_done==null) {
            throw new TodoNotFoundException(id); // Raise Not Found Exception
        } else {
            LocalDate done_date = LocalDate.now();
            to_done.setDone_date(done_date);
            to_done.setFlag("Done");
        }
    }
    @Override
    public void updateUndone(int id) {
        // Logic for dealing with deleted ids
        Todo to_undone = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_undone==null) {
            throw new TodoNotFoundException(id); // Raise Not Found Exception
        } else {
            to_undone.setDone_date(null);
            to_undone.setFlag("Undone");
        }
    }

    public void deleteTodo(int id) {
        // Logic for dealing with deleted ids
        Todo to_delete = todos.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
        if (to_delete==null) {
            throw new TodoNotFoundException(id); // Raise Not Found Exception
        } else {
            todos.remove(to_delete);
        }
    }

}
