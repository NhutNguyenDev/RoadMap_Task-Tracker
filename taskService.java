import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class taskService {

    ArrayList<task> listTask = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void createData() {
        LocalDateTime currentTime = LocalDateTime.now();

        task task1 = new task(1, "task1", "CREATED", currentTime, currentTime);
        task task2 = new task(2, "task2", "CREATED", currentTime, currentTime);
        task task3 = new task(3, "task3", "CREATED", currentTime, currentTime);
        task task4 = new task(4, "task4", "CREATED", currentTime, currentTime);
        task task5 = new task(5, "task5", "CREATED", currentTime, currentTime);

        listTask.add(task1);
        listTask.add(task2);
        listTask.add(task3);
        listTask.add(task4);
        listTask.add(task5);

    }

    public void addTask() {
        System.out.println("WHAT NAME OF TASK ?");
        String description = sc.nextLine();

        int id = listTask.size() + 1;
        String status = "CREATED";

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();

        try {

            task newTask = new task(id, description, status, currentTime, updateTime);

            listTask.add(newTask);

            System.out.println("ADD NEW TAS SUCCESS !!!");
            saveToFile();

            printAllTask();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateTask() {
        printAllTask();
        System.out.println("TYPE ID TO CHANGE NAME !");

        int idTaskChange = sc.nextInt();
        task taskMatching = null;
        taskMatching = findTaskMatching(idTaskChange);

        if (taskMatching != null) {
            sc.nextLine();
            System.out.println("WHAT NEW NAME ?");

            String newName = sc.nextLine();
            taskMatching.setDescription(newName);
            saveToFile();

            System.out.println("CHANGE NAME SUCCESS !!!");
        } else {
            System.out.println("WRONG ID: Don't change any thing !!!");
        }

    }

    public void deleteTask() {

        printAllTask();

        System.out.println("GIVE ID TO DELETE !");
        int idTaskDelete = sc.nextInt();
        sc.nextLine();

        task taskDelete = findTaskMatching(idTaskDelete);
        if (taskDelete != null) {
            ArrayList<task> newListTask = new ArrayList<>();
            for (task task : listTask) {
                if (task.getId() != idTaskDelete) {
                    task.setId(newListTask.size() + 1);
                    newListTask.add(task);
                }
            }

            listTask = newListTask;
            saveToFile();

            System.out.println("DELETE SUCCESS !");
        } else {
            System.out.println("WRONG ID: Don't delete any task !!!");
        }

    }

    public void markTaskInProgress() {

        // Show all task
        System.out.println("=================  TASK CREATED  =================");
        for (task task : listTask) {
            // System.out.println("Task trong file: "+ task.getStatus());
            if (task.getStatus().compareTo("CREATED")==0) {
                // System.out.println("no giong");
                System.out.println(task);
            }
        }
        System.out.println("=================  ============  =================");

        // Function main
        System.out.println("GIVE ID TASK TO MAKE \"PROGRESS\"");
        int idTaskMark = sc.nextInt();

        task taskMarkMatching = findTaskMatching(idTaskMark);
        if (taskMarkMatching != null && taskMarkMatching.getStatus().compareTo("CREATED")==0) {
            taskMarkMatching.setStatus("PROGRESS");
            updateTime(idTaskMark);
            saveToFile();

            System.out.println("MARK PROGRESS SUCCESS !");
        } else {
            System.out.println("WRONG ID: Don't mark any task !!!");
        }

    }

    public void markTaskDone() {

        listTasksInProgress();

        System.out.println("GIVE ID TASK TO MAKE \"DONE\"");
        int idTaskMark = sc.nextInt();

        task taskMarkMatching = findTaskMatching(idTaskMark);

        if (taskMarkMatching != null && taskMarkMatching.getStatus().compareTo("PROGRESS")==0) {

            taskMarkMatching.setStatus("DONE");
            updateTime(idTaskMark);
            saveToFile();


            System.out.println("MARK DONE SUCCESS !");
        } else {
            System.out.println("WRONG ID: Don't mark any task !!!");
        }

    }

    public void listAllTasks() {
        printAllTask();
    }

    public void listDoneTasks() {
        printTaskSpecificStatus("DONE");
    }

    public void listNotDoneTasks() {
        System.out.println("=================  TASK not DONE  =================");
        for (task task : listTask) {
            if (task.getStatus().compareTo("DONE") != 0) {
                System.out.println(task);
            }
        }
        System.out.println("=================  =============  =================");

    }

    public void listTasksInProgress() {
        printTaskSpecificStatus("PROGRESS");
    }

    // Private function
    private void printTaskSpecificStatus(String status) {
        System.out.println("=================  TASK in " + status + "  =================");
        for (task task : listTask) {
            if (task.getStatus().compareTo(status) == 0) {
                System.out.println(task);
            }
        }
        System.out.println("=================  ================  =================");
    }

    private void printAllTask() {
        System.out.println("=================  ALL TASK  =================");
        for (task t : listTask) {
            System.out.println(t);
        }
        System.out.println("=================  ========  =================");

    }

    private task findTaskMatching(int idTaskChange) {

        for (task task : listTask) {
            if (task.getId() == idTaskChange) {
                return task;
            }
        }
        return null;
    }

    private void updateTime(int idTaskChange) {
        LocalDateTime newTime = LocalDateTime.now();
        task taskUpdateTime = findTaskMatching(idTaskChange);
        taskUpdateTime.setUpdateAt(newTime);
    }

    public void saveToFile() {

        String listFormatJSON = "[\n";
        int countLengthListTask = 0;

        for (task task : listTask) {
            countLengthListTask++;
            int id = task.getId();
            String description = task.getDescription();
            String status = task.getStatus();
            LocalDateTime createAt = task.getCreateAt();
            LocalDateTime updateAt = task.getUpdateAt();

            String taskJSON = "{\n" +
                    "  \"id\": " + id + ",\n" +
                    "  \"description\": \"" + description + "\",\n" +
                    "  \"status\": \"" + status + "\",\n" +
                    "  \"createAt\": \"" + createAt + "\",\n" +
                    "  \"updateAt\": \"" + updateAt + "\"\n" +
                    "}";
            if (countLengthListTask != listTask.size()) {
                taskJSON += ",\n";
            }
            listFormatJSON += taskJSON;
        }
        // Add The last syntax for Array, it's "]"
        listFormatJSON += "\n]";
        // System.out.println(listFormatJSON);

        // Write to file
        String filePath = "./data.json";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(listFormatJSON);
            // System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readTaskFromFile() {
        System.out.println("Read from file:");
        String filePath = "./data.json";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read Start of Array, it's '['
            br.readLine();

            while ((line = br.readLine()) != null && line.compareTo("{") == 0) {

                // Read each value
                String id = br.readLine();
                String description = br.readLine();
                String status = br.readLine();
                String createAt = br.readLine();
                String updateAt = br.readLine();
                // Read Angle Braket , it's "}"
                br.readLine();

                // Cut value between ""
                id = id
                        .substring(getIndexOfSpace(id), getIndexOfComma(id));
                description = description
                        .substring(17, getIndexOfComma(description));
                status = status
                        .substring(12, getIndexOfComma(status));
                createAt = createAt
                        .substring(13, getIndexOfComma(createAt));
                updateAt = updateAt
                        .substring(13, updateAt.length());
                // Get actual value
                id = id.trim();
                description = description.trim().substring(1, description.length()-1);
                status = status.trim().substring(1, status.length()-1).trim();
                createAt = createAt.trim().substring(1, createAt.length()-2);
                updateAt = updateAt.trim().substring(1, updateAt.length()-2);

                LocalDateTime createAt1 = LocalDateTime.parse(createAt);
                LocalDateTime updateAt1 = LocalDateTime.parse(updateAt);

                // Add to listTask
                task taskAdd = new task(Integer.parseInt(id), description, status, createAt1, updateAt1);
                listTask.add(taskAdd);

                }
            // Check if read from file Success
            // System.out.println(line + ": if before is ], that read correct !!!");
            printAllTask();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIndexOfSpace(String data) {
        int index = data.lastIndexOf(" ");
        return index;

    }

    private int getIndexOfComma(String data) {
        return data.lastIndexOf(",");
    }

}
