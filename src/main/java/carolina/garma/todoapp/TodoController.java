package carolina.garma.todoapp;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(params = {"orden","flag","priority","content"})
    public List<Todo> getTodos(@RequestParam(defaultValue = "default") String orden,
                               @RequestParam(defaultValue = "all") String flag,
                               @RequestParam(defaultValue = "all") String priority,
                               @RequestParam(defaultValue = "none") String content) {

        List<Todo> todos = todoService.getTodos();

        // Content subsequence filters
        if (!content.equals("none")) {
            todos = todos.stream().filter(p->p.getContent().contains(content)).collect(Collectors.toList());
        }

        // Flag filters done/undone
        if (!flag.equals("all")) {
            todos = todos.stream().filter(p->p.getFlag().equals(flag)).collect(Collectors.toList());
        }

        // Priority filters
        if (!priority.equals("all")) {
            todos = todos.stream().filter(p->p.getPriority().equals(priority)).collect(Collectors.toList());
        }

        // Sorting filters
        if (orden.equals("priority")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getPriority)).collect(Collectors.toList());
        }else if (orden.equals("due_date")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getDue_date)).collect(Collectors.toList());
        }else if (orden.equals("priority-then-date")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getPriority).thenComparing(Todo::getDue_date)).collect(Collectors.toList());
        } else if (orden.equals("date-then-priority")) {
            return todos.stream().sorted(Comparator.comparing(Todo::getDue_date).thenComparing(Todo::getPriority)).collect(Collectors.toList());
        } else { // else is defaultValue="default"
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
        //ResponseEntity.status(HttpStatus.CREATED).body("Todo Created");// 201 is the response code

    }
    @PutMapping(path = "/{id}")
    public void updateTodo(@PathVariable("id") int id,
                           @RequestBody Todo todo
    )
    {
        todoService.updateTodo(id, todo);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTodo(@PathVariable ("id") int id){
        todoService.deleteTodo(id);
    }

    @PutMapping(path = "/{id}/done")
    public void updateDone(@PathVariable("id") int id) {
        todoService.updateDone(id);
    }

    @PutMapping(path = "/{id}/undone")
    public void updateUndone(@PathVariable("id") int id) {
        todoService.updateUndone(id);
    }
}
