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

    @GetMapping(params = {"orden","flag","priority","content","page"})
    public TodoPage<Todo> getTodos(@RequestParam(defaultValue = "default") String orden,
                               @RequestParam(defaultValue = "all") String flag,
                               @RequestParam(defaultValue = "all") String priority,
                               @RequestParam(defaultValue = "none") String content,
                               @RequestParam(defaultValue = "1") String page) {

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
            todos = todos.stream().sorted(Comparator.comparing(Todo::getPriority)).collect(Collectors.toList());
        }else if (orden.equals("due_date")) {
            todos=  todos.stream().sorted(Comparator.comparing(Todo::getDue_date)).collect(Collectors.toList());
        }else if (orden.equals("priority-then-date")) {
            todos=  todos.stream().sorted(Comparator.comparing(Todo::getPriority).thenComparing(Todo::getDue_date)).collect(Collectors.toList());
        } else if (orden.equals("date-then-priority")) {
            todos = todos.stream().sorted(Comparator.comparing(Todo::getDue_date).thenComparing(Todo::getPriority)).collect(Collectors.toList());
        } //else { // else is defaultValue="default"
            //return todos;
        //}
        //return todoService.getTodos();
        return todoService.getPages(todos,Integer.parseInt(page));
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    /*@GetMapping(path = "/page/{page_number}")
    public TodoPage<Todo> getPages(@PathVariable("page_number") int page_number) {
        return todoService.getPages(page_number);
    }*/

    @PostMapping
    public ResponseEntity<Object> addNewTodo(@RequestBody Todo todo){
        return todoService.addNewTodo(todo);// 201 is the response code

    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable("id") int id,
                           @RequestBody Todo todo
    )
    {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<Object> deleteTodo(@PathVariable ("id") int id){
        return todoService.deleteTodo(id);
    }

    @PutMapping(path = "/{id}/done")
    public ResponseEntity<Object> updateDone(@PathVariable("id") int id) {
        return todoService.updateDone(id);
    }

    @PutMapping(path = "/{id}/undone")
    public ResponseEntity<Object> updateUndone(@PathVariable("id") int id) {
        return todoService.updateUndone(id);
    }
}
