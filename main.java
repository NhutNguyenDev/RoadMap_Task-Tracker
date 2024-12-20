import java.util.Scanner;

public class main {
  public static void main(String[] args) {
    System.out.println("Welcom to Task-Tracker develop by DevNhutNguyen !!!");
    System.out.println("You can : \r\n" + //
        "1. add - update - delete   : Add, Update, and Delete tasks\r\n" + //
        "2. mark                    : Mark a task as in progress or done\r\n" + //
        "3. list-all                : List all tasks\r\n" + //
        "4. list-done               : List all tasks that are done\r\n" + //
        "5. list-not-done           : List all tasks that are not done\r\n" + //
        "6. list-progress           : List all tasks that are in progress\r\n" + //
        "7. exit                    : Exit Task Tracker");

    boolean isRunning = true;

    commandController command = new commandController();
    Scanner sc = new Scanner(System.in);
    command.createData();
    command.listAllTasks();
    try {
      while (isRunning) {

        System.out.println("What command ?");
        String commandInput = sc.nextLine();

        switch (commandInput) {
          case "add":
            command.addTask();
            break;

          case "update":
            command.updateTask();
            break;

          case "delete":
            command.deleteTask();
            break;

          case "mark":

            System.out.println("Type 1: mark Progress");
            System.out.println("Type 2: mark Done");
            System.out.println("Type anything: don't mark");
            int subCommand = sc.nextInt();
            sc.nextLine();
            
            if (subCommand == 1) {
              command.markTaskInProgress();
            } else if (subCommand == 2) {
              command.markTaskDone();
            } 

            break;

          case "list-all":
            command.listAllTasks();
            break;

          case "list-done":
            command.listDoneTasks();
            break;

          case "list-not-done":
            command.listNotDoneTasks();
            break;

          case "list-progress":
            command.listTasksInProgress();
            break;

          case "exit":
            System.out.println("Exiting Task Tracker. See You Again!");
            isRunning = false;
            break;

          default:
            System.out.println("Invalid choice! Please type a correct command below.");
            System.out.println(
                "1. add - update - delete   : Add, Update, and Delete tasks\r\n" + //
                    "2. mark                    : Mark a task as in progress or done\r\n" + //
                    "3. list-all                : List all tasks\r\n" + //
                    "4. list-done               : List all tasks that are done\r\n" + //
                    "5. list-not-done           : List all tasks that are not done\r\n" + //
                    "6. list-progress           : List all tasks that are in progress\r\n" + //
                    "7. exit                    : Exit Task Tracker");
        }

      }
      sc.close();

    } catch (Exception e) {
      System.out.println(e);
    }

  }
}