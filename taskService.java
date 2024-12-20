import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class taskService {

    ArrayList<task> listTask = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void createData(){

        task task1 = new task(1, "task1", "CREATED", null, null);
        task task2 = new task(2, "task2", "CREATED", null, null);
        task task3 = new task(3, "task3", "CREATED", null, null);
        task task4 = new task(4, "task4", "CREATED", null, null);
        task task5 = new task(5, "task5", "CREATED", null, null);
    
        listTask.add(task1);
        listTask.add(task2);
        listTask.add(task3);
        listTask.add(task4);
        listTask.add(task5);

    }
    public void addTask() {
        System.out.println("What name of this task ?");
        String description = sc.nextLine();

        int id = listTask.size() + 1;
        String status = "created";

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();

        try {

            task newTask = new task(id, description, status, currentTime, updateTime);

            listTask.add(newTask);

            printAllTask();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void updateTask() {
        printAllTask();
        System.out.println("What task want to change ? ( Give id of tag )");
        
        int idTaskChange = sc.nextInt();
        task taskMatching = null;
        taskMatching = findTaskMatching(idTaskChange); ;

        sc.nextLine();
        System.out.println("What thing you want to change");
        System.out.println("update : Change name task");
        System.out.println("1 : Update status to 'created'");
        System.out.println("2 : Update status to 'progress'");
        System.out.println("3 : Update status to 'done'");

        String command = sc.nextLine();
        switch (command) {
            case "update":

                System.out.println("What new name of task ?");
                String newName = sc.nextLine();

                taskMatching.setDescription(newName);
                System.out.println("Update name success");

                break;
            case "1":

                taskMatching.setStatus("CREATED");
                System.out.println("Change status Success !");
                break;

            case "2":

                taskMatching.setStatus("PROGRESS");
                System.out.println("Change status Success !");
                break;

            case "3":

                taskMatching.setStatus("DONE");
                System.out.println("Change status Success !");
                break;

            default:
                System.out.println("Don't change anything, out function update !");
                break;
        }
    }
    public void deleteTask() {

        printAllTask();

        System.out.println("Which task you want to delete ? ( Give id of task )");
        int idTaskDelete = sc.nextInt();
        sc.nextLine();

        ArrayList<task> newListTask = new ArrayList<>();
        for(task task : listTask){
            if(task.getId() != idTaskDelete){
                task.setId(newListTask.size() + 1);
                newListTask.add(task);
            }
        }

        listTask = newListTask;

        System.out.println("Delete task Success !");

    }
    public void markTaskInProgress() {

        System.out.println("=================  TASK CREATED  =================");
        for (task task : listTask) {
            if (task.getStatus() == "CREATED") {
                System.out.println(task);
            }
        }
        System.out.println("=================  ============  =================");
    
        System.out.println("What task mark in PROGRESS");
        int idTaskMark = sc.nextInt();

        task taskMarkMatching = findTaskMatching(idTaskMark);

        taskMarkMatching.setStatus("PROGRESS");
        System.out.println("Mark task Success !");

    }
    public void markTaskDone() {

        listTasksInProgress();

        System.out.println("What task mark in DONE");
        int idTaskMark = sc.nextInt();

        task taskMarkMatching = findTaskMatching(idTaskMark);

        
        taskMarkMatching.setStatus("DONE");

        System.out.println("Mark task Success !");
    }
    public void listAllTasks() {
        printAllTask();
    }
    public void listDoneTasks() {
        System.out.println("=================  TASK DONE  =================");
        for (task task : listTask) {
            if (task.getStatus() == "DONE") {
                System.out.println(task);
            }
        }
        System.out.println("=================  =========  =================");
    }
    public void listNotDoneTasks() {
        System.out.println("=================  TASK not DONE  =================");
        for (task task : listTask) {
            if (task.getStatus() != "DONE") {
                System.out.println(task);
            }
        }
        System.out.println("=================  =============  =================");

    }
    public void listTasksInProgress() {
        System.out.println("=================  TASK in PROGRESS  =================");
        for (task task : listTask) {
            if (task.getStatus() == "PROGRESS") {
                System.out.println(task);
            }
        }
        System.out.println("=================  ================  =================");
    }

    // Private function
    private void printAllTask() {
        System.out.println("=================  ALL TASK  =================");
        for (task t : listTask) {
            System.out.println(t);
        }
        System.out.println("=================  ========  =================");

    }
    private task findTaskMatching(int idTaskChange){

        for(task task : listTask){
            if(task.getId() == idTaskChange){
                return task;
            }
        }
        return null;
    }
}
