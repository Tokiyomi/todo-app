package carolina.garma.todoapp;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(params = {"orden","flag","priority"})
    public List<Todo> getTodos(@RequestParam(defaultValue = "default") String orden,
                               @RequestParam(defaultValue = "all") String flag,
                               @RequestParam(defaultValue = "all") String priority) {

        List<Todo> todos = todoService.getTodos();

        if (!flag.equals("all")) {
            todos = todos.stream().filter(p->p.getFlag().equals(flag)).collect(Collectors.toList());
        }

        if (!priority.equals("all")) {
            todos = todos.stream().filter(p->p.getPriority().equals(priority)).collect(Collectors.toList());
        }

        if (orden.equals("priority")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getPriority)).collect(Collectors.toList());
        }else if (orden.equals("due_date")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getDue_date)).collect(Collectors.toList());
        }else if (orden.equals("all")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getPriority).thenComparing(Todo::getDue_date)).collect(Collectors.toList());
        } else {
            return todos;
        }
        //return todoService.getTodos();
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
