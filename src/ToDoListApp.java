import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    static Connection connection = DatabaseConnector.connect();
    static void toDoList() {
        ManageDB manageDB = new ManageDB();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("To-Do List Application");
        System.out.println("1. Add a task");
        System.out.println("2. Edit a task");
        System.out.println("3. View tasks");
        System.out.println("4. Mark the task as complete");
        System.out.println("5. Exit");

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {/*Adding a task*/
                        System.out.print("Add a task: ");
                        scanner.nextLine();
                        String task = scanner.nextLine();
                        manageDB.addTask(task);
                    }
                    case 2 -> {
                        System.out.println("Choose an ID of your task to make editions.");
                        System.out.print("Enter ID: ");
                        int taskEdit = scanner.nextInt();
                        System.out.print("Update description: ");
                        scanner.nextLine();
                        String updatedDescription = scanner.nextLine();
                        manageDB.updateTaskDescription(taskEdit, updatedDescription);
                    }

                    case 3 -> manageDB.viewTasks();
                    case 4 -> {
                        System.out.println("Choose an ID of your task to mark as complete.");
                        System.out.print("Enter ID: ");
                        int taskEdit = scanner.nextInt();
                        Task taskToComplete = null;

                        // Iterate through the tasks using an enhanced for loop
                        for (Task task : tasks) {
                            // Checks if the task ID matches the user input
                            if (task.getTaskId() == taskEdit) {
                                taskToComplete = task; // Stores the matching task
                                break; // Exit the loop because we've found the task.
                            }
                        }
                        if (taskToComplete != null) {
                           manageDB.updateTaskStatus(taskEdit);
                        } else {
                            System.out.println("Task has not been found!");
                        }
                    }
                    case 5 -> {
                        System.out.println("GoodBye!");
                        System.exit(choice);
                        scanner.close();
                    }
                    default -> System.out.println("Invalid choice. Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }
}
