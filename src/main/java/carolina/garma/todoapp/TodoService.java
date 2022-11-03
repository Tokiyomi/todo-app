package carolina.garma.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;
//LocalDate.of(2000, 2, 14),
@Service
public class TodoService {

    //private static ArrayList<Todo> todos = new ArrayList<Todo>();
    private static ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(
                    //1L,
                    "ola",
                    "ow",
                    "owo",
                    LocalDate.of(2000, 2, 14),
                    LocalDate.of(2000, 2, 14),
                    LocalDate.of(2000, 2, 14)
                    ),
            new Todo(
                    //1L,
                    "ola2",
                    "ow2",
                    "owo2",
                    LocalDate.of(2000, 2, 14),
                    LocalDate.of(2000, 2, 14),
                    LocalDate.of(2000, 2, 14)
            )
            )
    );

    public List<Todo> getTodos() {
        return todos;
    }

    public void addNewTodo(Todo todo) {todos.add(todo);
    }

    public void updateTodo(int id, String content, String priority, LocalDate due_date) {
        for (int i=0;i<todos.size();i++){
            int temp_id = todos.get(i).getId();
            if (temp_id == id) {
                todos.get(i).setContent(content);
                todos.get(i).setPriority(priority);
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