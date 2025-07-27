
import java.time.LocalDate;

public class Task{
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private boolean isCompleted;

    public Task(String title, String description, LocalDate dueDate, String priority){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getTitle(){
        return title;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public String getPriority(){
        return priority;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    public void markCompleted(){
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "[ " + (isCompleted ? "✓" : "✗") + " ] " + title + " (" + priority + ") - Due: " + dueDate;
    }
    
    public String toFileFormat() {
        return title + "," + description + "," + dueDate + "," + priority + "," + isCompleted;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(",");
        Task t = new Task(parts[0], parts[1], LocalDate.parse(parts[2]), parts[3]);
        if (Boolean.parseBoolean(parts[4])) {
            t.markCompleted();
        }
        return t;
    }

}