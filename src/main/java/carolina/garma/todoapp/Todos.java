package carolina.garma.todoapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Todos {
    private static List<Todo> todos = new ArrayList<Todo>();


    public static void addNewTodo(Todo todo) {
        todos.add(todo);
    }


    public Todos(List<Todo> todos) {
        this.todos = todos;
    }

    public Todos() {
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

}
