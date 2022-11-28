package carolina.garma.todoapp;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Todo {

    /*
    <Todo> object class definition of setters, getters and constructors.
    Each <Todo> object has the following properties:
    - A unique and incremental ID (Atomic Integer) not null
    - A content of the todo (String) not null, not empty, size<=120
    - A priority level LOW/MEDIUM/HIGH  (enum variable) not null
    - A status flag DONE/UNDONE (enum variable) not null
    - A due date set by the user optionally (datetime variable)
    - A creation date set in the moment that a new todo is added to the todos list (datetime variable) not null
    - A done date set when the user changes the flag status to DONE (datetime variable)
     */

    // Variable declarations
    enum priority_level {
        HIGH, MEDIUM, LOW;
    }
    enum done_enum {
        DONE, UNDONE;
    }
    private int id;
    private String content;
    private LocalDateTime creation_date;
    private LocalDateTime done_date;
    private LocalDateTime due_date;

    // Initializing default variables for ID, priority level and status
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1); // start incremental at 1
    private priority_level priority = priority_level.LOW; // if the user does not specify a priority, this will be low
    private done_enum flag = done_enum.UNDONE; // the todo is always created with an undone status

    // Class constructor
    public Todo(String content, done_enum flag, priority_level priority, LocalDateTime creation_date, LocalDateTime done_date, LocalDateTime due_date) {
        setAtomicId();
        this.id = getId();
        this.content = content;
        this.flag = flag;
        this.priority = priority;
        this.creation_date = creation_date;
        this.done_date = done_date;
        this.due_date = due_date;
    }

    // Class empty constructor
    public Todo() {
    }

    // ID setters and getters (setAtomicId function is used to autoincrement our ID variable)
    public void setAtomicId() {
        this.id = ID_GENERATOR.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Status flag setter and getter
    public done_enum getFlag() {
        return flag;
    }
    public void setFlag(done_enum flag) {
        this.flag = flag;
    }

    // Content setter and getter
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    // Priority level setter and getter
    public priority_level getPriority() {
        return priority;
    }

    public void setPriority(priority_level priority) {
        this.priority = priority;
    }

    // Creation date setter and getter
    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    // Done date setter and getter
    public LocalDateTime getDone_date() {
        return done_date;
    }

    public void setDone_date(LocalDateTime done_date) {
        this.done_date = done_date;
    }

    // Due date setter and getter
    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }
}
