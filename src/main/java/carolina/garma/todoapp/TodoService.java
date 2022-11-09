package carolina.garma.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//LocalDate.of(2000, 2, 14),
@Service
public class TodoService {
    //@Autowired
    private final TodoRepository todorepository;
    public TodoService(TodoRepository todorepository) {
        this.todorepository = todorepository;
    }

    public List<Todo> getTodos() {
        return todorepository.getTodos();
    }
    public ResponseEntity<Object> addNewTodo(Todo todo) {return todorepository.addNewTodo(todo);}

    public ResponseEntity<Object> updateTodo(int id, Todo todo) {return todorepository.updateTodo(id,todo);}

    public ResponseEntity<Object> updateDone(int id) {return todorepository.updateDone(id);}

    public ResponseEntity<Object> updateUndone(int id) {
        return todorepository.updateUndone(id);
    }

    public ResponseEntity<Object> deleteTodo(int id) {
        return todorepository.deleteTodo(id);
    }
}
