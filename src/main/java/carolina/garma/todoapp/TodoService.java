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
    //@Autowired
    private final TodoRepository todorepository;
    public TodoService(TodoRepository todorepository) {
        this.todorepository = todorepository;
    }

    public List<Todo> getTodos() {
        return todorepository.getTodos();
    }
    public void addNewTodo(Todo todo) {todorepository.addNewTodo(todo);}

    public void updateTodo(int id, Todo todo) {todorepository.updateTodo(id,todo);}

    public void updateDone(int id) {todorepository.updateDone(id);}

    public void updateUndone(int id) {
        todorepository.updateUndone(id);
    }

    public void deleteTodo(int id) {
        todorepository.deleteTodo(id);
    }
}
