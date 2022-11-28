package carolina.garma.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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

    public TodoPage<Todo> getPages(List<Todo> todos, int page_number) {
        return todorepository.getPages(todos, page_number);
    }

    public ResponseEntity<Object> addNewTodo(Todo todo) {
        return todorepository.addNewTodo(todo);
    }

    public ResponseEntity<Object> updateTodo(int id, Todo todo) {
        return todorepository.updateTodo(id, todo);
    }

    public ResponseEntity<Object> updateDone(int id) {
        return todorepository.updateDone(id);
    }

    public ResponseEntity<Object> updateUndone(int id) {
        return todorepository.updateUndone(id);
    }

    public ResponseEntity<Object> deleteTodo(int id) {
        return todorepository.deleteTodo(id);
    }

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
        of finished todos, globally and by priority
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
