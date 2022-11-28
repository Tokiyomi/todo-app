package carolina.garma.todoapp;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TodoValidation {
    /*
    Class in charge of performing todo content validations over possible new
    or edited todos before definitely adding or editing them in the todos list.
     */

    public static Map<String, Object> validateTodo(Todo todo, ArrayList<Todo> todos) {
        /*
        Method that performs content validations (not null, not empty, not duplicated,
        size<=120 chars) over new todo candidates.
        If the todo is accepted, an empty error map is returned,
        if the todo is rejected, a map containing the failed validation message is returned
         */

        Map<String, Object> map = new HashMap<String, Object>();
        List<Todo> duplicated_content = todos.stream().filter(obj -> obj.getContent().equals(todo.getContent())).toList();
        //boolean flag = true;
        if (todo.getContent()!=null) {
            int strLen = todo.getContent().length();
            boolean atleastOneAlpha = todo.getContent().matches(".*[a-zA-Z]+.*");
            if (strLen>120){
                map.put("Invalid content", "Todo content must be equal or lesser than 120 chars");
                //flag = false;
            } else if (strLen==0) {
                map.put("Invalid content", "Todo content must not be empty");
                //flag = false;
            } else if (duplicated_content.size()>=1) {
                map.put("Invalid content", "Todo content already exist");
            } else if (!atleastOneAlpha) {
                map.put("Invalid content", "Todo content should content at least 1 alphabet char");
            }
        } else if (todo.getContent()==null) {map.put("Invalid content", "Todo content should not be null");}

        return map;
    }

    public static Map<String, Object> validateEditedTodo(Todo todo, ArrayList<Todo> todos, Todo to_update) {
        /*
        Method that performs content validations (not null, not empty, not duplicated,
        size<=120 chars) over editable todo candidates.
        If the todo is accepted, an empty error map is returned,
        if the todo is rejected, a map containing the failed validation message is returned

        The difference between this method and the previous one, is that we add a duplication Exception
        in which if the content passed as an edition is the same as the original, no error is returned.
        However we still can get this validation error if the content is duplicated in the other todo objects
         */

        Map<String, Object> map = new HashMap<String, Object>();

        if (!Objects.equals(todo.getContent(), to_update.getContent())) {
            List<Todo> duplicated_content = todos.stream().filter(obj -> obj.getContent().equals(todo.getContent())).toList();
            int strLen = todo.getContent().length();
            boolean atleastOneAlpha = todo.getContent().matches(".*[a-zA-Z]+.*");
            if (strLen>120){
                map.put("Invalid content", "Todo content must be equal or lesser than 120 chars");
                //flag = false;
            } else if (strLen==0) {
                map.put("Invalid content", "Todo content must not be empty");
                //flag = false;
            } else if (duplicated_content.size()>=1) {
                map.put("Invalid content", "Todo content already exist");
            } else if (!atleastOneAlpha) {
                map.put("Invalid content", "Todo content should content at least 1 alphabet char");
            }
        } else if (todo.getContent()==null) {map.put("Invalid content", "Todo content should not be null");}

        Todo.done_enum done_flag = todo.getFlag();
        LocalDateTime done_date = todo.getDone_date();
        if (done_flag.equals(Todo.done_enum.DONE)&done_date==null) {
            map.put("Invalid done date", "You should specify a done date");
        }

        return map;
    }
}
