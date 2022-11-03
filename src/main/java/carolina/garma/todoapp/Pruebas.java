package carolina.garma.todoapp;

import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Pruebas {
    static ArrayList<Todo> todos = new ArrayList<>(Arrays.asList(
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
                todos.get(i).setPriority("pruebita");
                todos.get(i).setDue_date(LocalDate.of(2010, 2, 14));
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
