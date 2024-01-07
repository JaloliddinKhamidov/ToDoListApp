import java.sql.*;
import java.util.*;

public class ManageDB {
    Task taskObject;
    ArrayList<Task> tasks = new ArrayList<>();
    static Connection connection = DatabaseConnector.connect();
    //Add a Task
    public void addTask(String task){
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO tasks (description, completed) VALUES (?, false)")) {
            preparedStatement.setString(1, task);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Task newTask = new Task(tasks.size() + 1, new StringBuffer(task));
        tasks.add(newTask);
        System.out.println("Task has been added successfully!");
    }

    //update Task Status
    public void updateTaskStatus(int taskToComplete){
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE tasks SET completed = true WHERE task_id = ?")) {
                    preparedStatement.setInt(1, taskToComplete);
                    int updatedRows = preparedStatement.executeUpdate();

                    if (updatedRows > 0) {
                        // Update task status in the local list
                        taskObject.setCompleted(true);
                        System.out.printf("Task: %d has been marked as complete! \n", taskToComplete);
                    } else {
                        System.out.println("Task update in the database failed!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
    public void updateTaskDescription(int taskEdit, String updatedDescription){
        // Update task description n the database
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE tasks SET description = ? WHERE task_id = ?")) {
            preparedStatement.setString(1, updatedDescription);
            preparedStatement.setInt(2, taskEdit);
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                System.out.printf("Task: %d has been successfully updated -> %s \n", taskEdit, taskObject.getDescription());
            }else{
                System.out.println("Task update in the database failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewTasks(){
        // Retrieve tasks from the database
        try (Statement statement = connection.createStatement()) {
            System.out.println("Your tasks: ");
            // Execute a SQL query to select all tasks from the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks"); //ResultSet can store data in a chunk of structure
            while (resultSet.next()) {
                // Retrieve task details from the ResultSet
                int taskId = resultSet.getInt("task_id");
                String description = resultSet.getString("description");
                boolean completed = resultSet.getBoolean("completed");
                System.out.println("Task ID: " + taskId + ", Description: " + description + ", Completed: " + completed);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQLException that occurs during database interaction
        }
    }
}

