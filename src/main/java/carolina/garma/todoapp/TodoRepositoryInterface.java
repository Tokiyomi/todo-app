package carolina.garma.todoapp;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoRepositoryInterface {
    List<Todo> getTodos();

    ResponseEntity<Object> addNewTodo(Todo todo);

    ResponseEntity<Object> updateTodo(int id, Todo todo);

    ResponseEntity<Object> deleteTodo(int id);

    ResponseEntity<Object> updateDone(int id);

    ResponseEntity<Object> updateUndone(int id);
}
