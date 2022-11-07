package carolina.garma.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//LocalDate.of(2000, 2, 14),
@Service
public class TodoService {

    //private static ArrayList<Todo> todos = new ArrayList<Todo>();
    //Todo todito;
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

    public List<Todo> getTodos() {
        return todos;
    }

    public void addNewTodo(Todo todo) {
        todo.setAtomicId();
        LocalDate creation_date = LocalDate.now();
        todo.setCreation_date(creation_date);
        todos.add(todo);
    }

    public void updateTodo(int id, Todo todo) {
        for (int i=0;i<todos.size();i++){
            int temp_id = todos.get(i).getId();
            if (temp_id == id) {
                if (todo.getContent()!=null) {todos.get(i).setContent(todo.getContent());}
                if (todo.getPriority()!=null) {todos.get(i).setPriority(todo.getPriority());}
                if (todo.getDue_date()!=null) {todos.get(i).setDue_date(todo.getDue_date());}
                break;
            } else {
                continue;
            }

        }
    }

    public void updateDone(int id) {
        for (int i=0;i<todos.size();i++){
            int temp_id = todos.get(i).getId();
            if (temp_id == id) {
                LocalDate done_date = LocalDate.now();
                todos.get(i).setDone_date(done_date);
                todos.get(i).setFlag("Done");
                break;
            } else {
                continue;
            }

        }
    }

    public void updateUndone(int id) {
        for (int i=0;i<todos.size();i++){
            int temp_id = todos.get(i).getId();
            if (temp_id == id) {
                todos.get(i).setDone_date(null);
                todos.get(i).setFlag("Undone");
                break;
            } else {
                continue;
            }

        }
    }

}
