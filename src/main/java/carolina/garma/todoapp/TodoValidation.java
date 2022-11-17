package carolina.garma.todoapp;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TodoValidation {

    public static Map<String, Object> validateTodo(Todo todo, ArrayList<Todo> todos) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<Todo> duplicated_content = todos.stream().filter(obj -> obj.getContent().equals(todo.getContent())).toList();
        //boolean flag = true;
        if (todo.getContent()!=null) {
            int strLen = todo.getContent().length();
            if (strLen>120){
                map.put("Invalid content", "Todo content must be equal or lesser than 120 chars");
                //flag = false;
            } else if (strLen==0) {
                map.put("Invalid content", "Todo content must not be empty");
                //flag = false;
            } else if (duplicated_content.size()>=1) {
                map.put("Invalid content", "Todo content already exist");
            }
        } else if (todo.getContent()==null) {map.put("Invalid content", "Todo content should not be null");}

        Todo.done_enum done_flag = todo.getFlag();
        LocalDateTime done_date = todo.getDone_date();
        if (done_flag.equals(Todo.done_enum.DONE)&done_date==null) {
            map.put("Invalid done date", "You should specify a done date");
            //flag = false;
        }
        LocalDateTime due_date = todo.getDue_date();
        LocalDateTime creation_date = todo.getCreation_date();
        if (due_date!=null) {
            if (due_date.isBefore(creation_date)) {
                map.put("Invalid due date", "Due date should be after or equal to creation date");}
            if (done_date!=null) {
                if (due_date.isAfter(done_date)) {
                    map.put("Invalid due date", "Due date should be before or equal to done date");}
            }
        }


        return map;
    }
}
