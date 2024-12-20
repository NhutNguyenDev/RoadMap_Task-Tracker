import java.util.Scanner;

public class commandController {

    taskService taskService = new taskService();
    task[] listTask = new task[10];

    public void createData(){
        taskService.createData();
    }
    public void addTask() {
       taskService.addTask();
    }

    public void updateTask() {
        taskService.updateTask();
    }

    public void deleteTask() {
        taskService.deleteTask();
    }

    public void markTaskInProgress() {
        taskService.markTaskInProgress();
    }

    public void markTaskDone() {
        taskService.markTaskDone();
    }

    public void listAllTasks() {
        taskService.listAllTasks();
    }

    public void listDoneTasks() {
        taskService.listDoneTasks();
    }

    public void listNotDoneTasks() {
        taskService.listNotDoneTasks();
    }

    public void listTasksInProgress() {
        taskService.listTasksInProgress();
    }
}
