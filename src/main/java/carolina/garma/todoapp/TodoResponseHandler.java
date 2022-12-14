package carolina.garma.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TodoResponseHandler {
    /*
    Class that is in charge of generating custom responses after performing validations
    Depending on the result of the validation, a specific message and http status will be passed
    to the generateResponse() method that will store also the todo in question and a
    "validation message" key containing the errors detected if any
     */
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, Map validation) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        if (validation != null) {
            map.put("validation message", validation);
        }
        return new ResponseEntity<Object>(map,status);
    }
}
