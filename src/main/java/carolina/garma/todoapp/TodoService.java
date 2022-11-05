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
        todos.add(todo);
    }

    public void updateTodo(int id, Todo todo) {
        for (int i=0;i<todos.size();i++){
            int temp_id = todos.get(i).getId();
            if (temp_id == id) {
                todos.get(i).setContent(todo.getContent());
                todos.get(i).setPriority(todo.getPriority());
                //todos.get(i).setDue_date(due_date);
            } else {
                continue;
            }

        }
    }

    /*public void initializeTodo() {
        Todo todo1 = new Todo(
                //1L,
                "ola",
                "ow",
                "owo",
                LocalDate.of(2000, 2, 14),
                LocalDate.of(2000, 2, 14),
                LocalDate.of(2000, 2, 14)
        );
        Todo todo2 = new Todo(
                //1L,
                "ola2",
                "ow2",
                "owo2",
                LocalDate.of(2000, 2, 14),
                LocalDate.of(2000, 2, 14),
                LocalDate.of(2000, 2, 14)
        );
        todos.add(todo1);
        todos.add(todo2);
    }*/

}
