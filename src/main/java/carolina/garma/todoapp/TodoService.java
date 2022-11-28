package carolina.garma.todoapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TodoService {
    /*
    Todo service component which is connected to the Todo Repository
    and is in charge of performing business logic computations
    (average finish time is computed here)
    */

    // Declare a TodoRepository variable to connect with Repository layer
    @Autowired
    private final TodoRepository todorepository;

    // Repository constructor
    public TodoService(TodoRepository todorepository) {
        this.todorepository = todorepository;
    }

    // Service call to the getTodos() repository method that returns the list of todos
    public List<Todo> getTodos() {
        return todorepository.getTodos();
    }

    // Service call to the getPages() repository method that returns the requested page view
    public TodoPage<Todo> getPages(List<Todo> todos, int page_number) {
        return todorepository.getPages(todos, page_number);
    }

    // Service call to the addNewTodo() repository method to validate and add a new element
    // returns a Response Entity of 200 if success, 400 if it was a bad request
    public ResponseEntity<Object> addNewTodo(Todo todo) {
        return todorepository.addNewTodo(todo);
    }

    // Service call to the updateTodo() repository method to validate and edit an existing todo
    // returns a Response Entity of 200 if success, 404 if the id was not found and 404 otherwise
    public ResponseEntity<Object> updateTodo(int id, Todo todo) {
        return todorepository.updateTodo(id, todo);
    }

    // Service call to the updateDone() repository method to validate and mark as done an existing todo
    // returns a Response Entity of 200 if success, 404 if the id was not found
    public ResponseEntity<Object> updateDone(int id) {
        return todorepository.updateDone(id);
    }

    // Service call to the updateUndone() repository method to validate and mark as undone an existing todo
    // returns a Response Entity of 200 if success, 404 if the id was not found
    public ResponseEntity<Object> updateUndone(int id) {
        return todorepository.updateUndone(id);
    }

    // Service call to the deleteTodo() repository method to delete an existing todo
    // returns a Response Entity of 200 if success, 404 if the id was not found
    public ResponseEntity<Object> deleteTodo(int id) {
        return todorepository.deleteTodo(id);
    }

    // Service method that stores the finish time metrics for each priority group after calling getAvg()
    // It returns a Map Collections that has the priority group as key and the average finish time
    // as value in days, hours and minutes
    public Map<String, Object> createTimeLists() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Todo> total_list = todorepository.getTodos().stream().filter(p -> Objects.nonNull(p.getDone_date())).toList();;
        List<Todo> low_list = total_list.stream().filter(p->p.getPriority().equals(Todo.priority_level.LOW)).toList();
        List<Todo> med_list = total_list.stream().filter(p->p.getPriority().equals(Todo.priority_level.MEDIUM)).toList();
        List<Todo> high_list = total_list.stream().filter(p->p.getPriority().equals(Todo.priority_level.HIGH)).toList();

        timeAvg total_avg = getAvg(total_list, "GLOBAL");
        timeAvg low_avg = getAvg(low_list, "LOW");
        timeAvg med_avg = getAvg(med_list, "MEDIUM");
        timeAvg high_avg = getAvg(high_list, "HIGH");

        map.put("GLOBAL",total_avg);
        map.put("LOW",low_avg);
        map.put("MEDIUM",med_avg);
        map.put("HIGH",high_avg);

        //return List.of(total_avg, low_avg, med_avg, high_avg);
        return map;
    }

    public timeAvg getAvg(List<Todo> todos, String priority) {
        /*
        Function that computes time between creation and done dates
        of finished todos by the priority level provided
        */
        long sum = 0;
        for (Todo todo : todos) {

            LocalDateTime creation = todo.getCreation_date();
            LocalDateTime done = todo.getDone_date();
            Duration duration = Duration.between(creation, done);

            long temp_seconds = duration.getSeconds();
            //diff_list.add(temp_seconds);
            sum += temp_seconds;
            
        }
        if (todos.size()>0) {
            long seconds = sum/todos.size();

            long days = seconds / (60 * 60 * 24);
            seconds = seconds - days * (60 * 60 * 24);

            long hours = seconds / (60 * 60);
            seconds = seconds - hours * (60 * 60);

            long minutes = seconds / 60;
            seconds = seconds - minutes * 60;

            return new timeAvg(seconds, minutes, hours, days, priority);
        }
        else {
            return new timeAvg(0,0,0,0, priority);
        }
    }
}
