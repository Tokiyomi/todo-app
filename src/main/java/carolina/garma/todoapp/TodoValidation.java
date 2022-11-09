package carolina.garma.todoapp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TodoValidation {
    public static Map<String, Object> validateTodo(Todo todo) {
        Map<String, Object> map = new HashMap<String, Object>();
        //boolean flag = true;
        int strLen = todo.getContent().length();
        if (strLen>120){
            map.put("Invalid content", "Todo content must be equal or lesser than 120 chars");
            //flag = false;
        } else if (strLen==0) {
            map.put("Invalid content", "Todo content must not be empty");
            //flag = false;
        }
        String done_flag = todo.getFlag();
        LocalDate done_date = todo.getDone_date();
        if (done_flag.equals("Done")&done_date==null) {
            map.put("Invalid done date", "You should specify a done date");
            //flag = false;
        }
        return map;
    }
}
