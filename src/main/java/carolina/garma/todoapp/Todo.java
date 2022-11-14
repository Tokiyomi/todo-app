package carolina.garma.todoapp;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Todo {

    enum priority_level {
        LOW, MEDIUM, HIGH;
    }
    enum done_flag {
        DONE, UNDONE;
    }
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private int id; // Incremental id, starts at 1
    private String content;
    private done_flag flag = done_flag.UNDONE;
    //private String priority = carolina.garma.todoapp.Todo.priority.LOW;

    private priority_level priority = priority_level.LOW;

    private LocalDateTime creation_date;
    private LocalDateTime done_date;
    private LocalDateTime due_date;

    public Todo() {
    }

    public Todo(String content, done_flag flag, priority_level priority, LocalDateTime creation_date, LocalDateTime done_date, LocalDateTime due_date) {
        setAtomicId();
        this.id = getId();
        this.content = content;
        this.flag = flag;
        this.priority = priority;
        this.creation_date = creation_date;
        this.done_date = done_date;
        this.due_date = due_date;
    }
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public done_flag getFlag() {
        return flag;
    }

    public priority_level getPriority() {
        return priority;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public LocalDateTime getDone_date() {
        return done_date;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAtomicId() {
        this.id = ID_GENERATOR.getAndIncrement();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFlag(done_flag flag) {
        this.flag = flag;
    }

    public void setPriority(priority_level priority) {
        this.priority = priority;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public void setDone_date(LocalDateTime done_date) {
        this.done_date = done_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

}
