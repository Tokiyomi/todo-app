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

    /*private List<Todo> todos = List.of(new Todo(
            1L,
            "ola",
            "ow",
            "owo",
            LocalDate.of(2000, 2, 14),
            LocalDate.of(2000, 2, 14),
            LocalDate.of(2000, 2, 14))
    );*/

    private ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(
                    1L,
                    "ola",
                    "ow",
                    "owo",
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

}
