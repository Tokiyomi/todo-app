package carolina.garma.todoapp;

import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Pruebas {
    static ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(
                    //1L,
                    "ola",
                    //"UNDONE",
                    Todo.done_enum.DONE,
                    //"HIGH",
                    Todo.priority_level.HIGH,
                    LocalDateTime.of(2000, 2, 14,0,0,0),
                    LocalDateTime.of(2000, 2, 14,0,0,0),
                    LocalDateTime.of(2000, 2, 14,0,0,0)
            ),
            new Todo(
                    //1L,
                    "ola2",
                    //"DONE",
                    Todo.done_enum.DONE,
                    Todo.priority_level.MEDIUM,
                    //"MEDIUM",
                    LocalDateTime.of(2000, 2, 14,0,0,0),
                    LocalDateTime.of(2000, 2, 14,0,0,0),
                    LocalDateTime.of(2000, 2, 14,0,0,0)
            )
    )
    );

    public static void main(String args[]) {
        int id = 2;
        for (int i=0;i<todos.size();i++){
            System.out.println(todos.get(i).getContent());
            todos.get(i).setContent(String.valueOf(i));
            System.out.println(todos.get(i).getContent());

        }
        for (int i=0;i<todos.size();i++){
            int temp_id = todos.get(i).getId();
            if (temp_id == id) {
                todos.get(i).setContent("pruebita");
                todos.get(i).setPriority(Todo.priority_level.HIGH);
                todos.get(i).setDue_date(LocalDateTime.of(2010, 2, 14,0,0,0));
            } else {
                continue;
            }

        }
        for (int i=0;i<todos.size();i++){
            System.out.println(todos.get(i).getContent());
            todos.get(i).setContent(String.valueOf(i));
            System.out.println(todos.get(i).getContent());

        }
    }
}
