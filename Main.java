
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n========= TASK MANAGER =========");
            System.out.println("1. Add New Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dateStr = sc.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = sc.nextLine();

                    LocalDate dueDate = LocalDate.parse(dateStr);
                    Task task = new Task(title, desc, dueDate, priority);
                    manager.addTask(task);
                    break;

                case 2:
                    manager.listAllTasks();
                    break;

                case 3:
                    System.out.print("Enter task number to mark completed: ");
                    int completeIndex = sc.nextInt();
                    manager.markTaskCompleted(completeIndex);
                    break;

                case 4:
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = sc.nextInt();
                    manager.deleteTask(deleteIndex);
                    break;

                case 5:
                    manager.saveTasksToFile();
                    System.out.println("Exiting... Tasks saved.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
