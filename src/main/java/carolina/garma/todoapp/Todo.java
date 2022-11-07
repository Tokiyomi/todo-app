package carolina.garma.todoapp;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Todo {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private int id; // Incremental id, starts at 1
    private String content;
    private String flag = "Undone";
    private String priority;
    private LocalDate creation_date;
    private LocalDate done_date;
    private LocalDate due_date;
    public Todo() {
    }

    public Todo(String content, String flag, String priority, LocalDate creation_date, LocalDate done_date, LocalDate due_date) {
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

    public String getFlag() {
        return flag;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public LocalDate getDone_date() {
        return done_date;
    }

    public LocalDate getDue_date() {
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

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public void setDone_date(LocalDate done_date) {
        this.done_date = done_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

}
