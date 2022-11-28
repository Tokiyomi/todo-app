package carolina.garma.todoapp;

public class timeAvg {
    /*
    Class that stores the average time metrics for finished todos.
    This time metrics are given in days, hours, minutes and seconds.
    Additionally, it has a String label to specify the group metric by priority
     */

    long seconds;
    long minutes;
    long hours;
    long days;

    String priority;

    public timeAvg() {
    }

    public timeAvg(long seconds, long minutes, long hours, long days, String priority) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
        this.priority = priority;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
