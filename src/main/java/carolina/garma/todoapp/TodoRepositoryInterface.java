package carolina.garma.todoapp;

import java.util.List;

public interface TodoRepositoryInterface {
    List<Todo> getTodos();

    void addNewTodo(Todo todo);

    void updateTodo(int id, Todo todo);

    void deleteTodo(int id);

    void updateDone(int id);

    void updateUndone(int id);
}
