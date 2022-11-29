package carolina.garma.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController // Controller annotation to let Spring Boot know this is our controller component
@RequestMapping(path="/todos") // General path that each request must have
@CrossOrigin // Connects frontend with backend
public class TodoController {
    /*
    Todo controller component which is in charge of providing access
    and managing data requests, this component connects our backend with
    the frontend
    */
    @Autowired
    private final TodoService todoService; // Declare a TodoService variable to connect with our Service layer

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService; // TodoService constructor
    }

    // GET request without any parameter as input, it returns the entire todos list.
    // This is how data is retrieved before applying any filtering, sorting and pagination view
    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    // GET request that manages filters, sorting and pagination views as a URI parameter.
    // Returns a TodoPage object which is a view of the current page that contains at most 10 todos
    @GetMapping(params = {"orden","flag","priority","content","page","asc"})
    public TodoPage<Todo> getTodos(@RequestParam(defaultValue = "default") String orden,
                               @RequestParam(defaultValue = "all") String flag,
                               @RequestParam(defaultValue = "all") String priority,
                               @RequestParam(defaultValue = "") String content,
                               @RequestParam(defaultValue = "1") String page,
                               @RequestParam(defaultValue = "false") String asc
    ) {

        List<Todo> todos = todoService.getTodos(); // Get our current todos list

        // Content subsequence filters
        if (!content.equals("")) {
            todos = todos.stream().filter(p->p.getContent().contains(content)).collect(Collectors.toList());
        }

        // Flag filters done/undone
        if (!flag.equals("all")) {
            todos = todos.stream().filter(p->p.getFlag().equals(Todo.done_enum.valueOf(flag))).collect(Collectors.toList());
        }

        // Priority filters
        if (!priority.equals("all")) {
            todos = todos.stream().filter(p->p.getPriority().equals(Todo.priority_level.valueOf(priority))).collect(Collectors.toList());
        }

        // Sorting filters
        if (orden.equals("priority")) {
            todos = todos.stream().sorted(Comparator.comparing(Todo::getPriority)).collect(Collectors.toList());
        }else if (orden.equals("due_date")) {
            todos = todos.stream().filter(p->p.getDue_date()!=null).collect(Collectors.toList());
            todos=  todos.stream().sorted(Comparator.comparing(Todo::getDue_date)).collect(Collectors.toList());
        }else if (orden.equals("priority-then-date")) {
            todos = todos.stream().filter(p->p.getDue_date()!=null).collect(Collectors.toList());
            todos=  todos.stream().sorted(Comparator.comparing(Todo::getPriority).thenComparing(Todo::getDue_date)).collect(Collectors.toList());
        } else if (orden.equals("date-then-priority")) {
            todos = todos.stream().filter(p->p.getDue_date()!=null).collect(Collectors.toList());
            todos = todos.stream().sorted(Comparator.comparing(Todo::getDue_date).thenComparing(Todo::getPriority)).collect(Collectors.toList());
        }

        // Asc filters
        if (asc.equals("true")) {
           Collections.reverse(todos);
        }

        return todoService.getPages(todos,Integer.parseInt(page)); // return requested page view
    }

    // GET request that returns a list containing time avg metrics for all groups (global, low, medium and high)
    @GetMapping(path = "avg")
    public Map<String, Object> getAvg() {
        return todoService.createTimeLists();
    }

    // POST request that returns a Response Entity when the
    // todo is created, validated and added to the todos list
    // 201 is the response code when success, 400 otherwise
    @PostMapping
    public ResponseEntity<Object> addNewTodo(@RequestBody Todo todo) {
        return todoService.addNewTodo(todo);
    }

    // PUT request that modifies an existing todo.
    // It receives as input the todo's ID and a json-like object containing the modified properties
    // If validations succeed, the todo is updated and it returns a 200 response code, 400/404 otherwise
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable("id") int id,
                           @RequestBody Todo todo
    )
    {
        return todoService.updateTodo(id, todo);
    }

    // PUT request to update the todo status as done, when this happens a done date is also generated
    // receives the todo's ID as input
    // It returns a 200 status code when success, 404 otherwise
    @PutMapping(path = "/{id}/done")
    public ResponseEntity<Object> updateDone(@PathVariable("id") int id) {
        return todoService.updateDone(id);
    }

    // PUT request to update the todo status as undone, when this happens a done date is set as null
    // receives the todo's ID as input
    // It returns a 200 status code when success, 404 otherwise
    @PutMapping(path = "/{id}/undone")
    public ResponseEntity<Object> updateUndone(@PathVariable("id") int id) {
        return todoService.updateUndone(id);
    }

    // PUT request to delete a todo object from the todos list, receives the todo's ID as input
    // After validation, it returns a 200 status code when success, 404 otherwise
    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<Object> deleteTodo(@PathVariable ("id") int id){
        return todoService.deleteTodo(id);
    }
}
