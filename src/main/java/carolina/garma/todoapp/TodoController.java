package carolina.garma.todoapp;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(path="/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }


    @PostMapping
    public void addNewTodo(@RequestBody Todo todo){
        todoService.addNewTodo(todo);

    }
    @PutMapping(path = "/{id}")
    public void updateTodo(@PathVariable("id") int id,
                           //@RequestParam(required = false) String content,
                           //@RequestParam(required = false) String priority,
                           //@RequestParam(required = false) LocalDate due_date
                           @RequestBody Todo todo
    )
    {
        //todoService.updateTodo(id, content, priority, due_date);
        todoService.updateTodo(id, todo);
    }
}
