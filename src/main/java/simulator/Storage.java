package simulator;

import exception.DukeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import java.util.Scanner;

import ui.Ui;

/**
 * Class <code>Storage</code> deals with loading tasks from file and saving taks in the file.
 * Contains two method <code>load</code> and <code>save</code>.
 */
public class Storage {
    private File data;

    /**
     * Constructs a storage with file to be loaded or saved initialized.
     */
    public Storage() {
        data = new File("./data.txt");
    }

    /**
     * Loads from the file into the the specified <code>list</code> and returns a <code>TaskList</code>.
     *
     * @param list tasks loaded.
     * @return loaded list.
     */
    public TaskList load(TaskList list) {
        try {
            if (data.createNewFile()) {
                Ui.printBox("No Save Record Detected... \n"
                        + "     Creating New List! :)");
            } else {
                Ui.printBox("Saved Record Detected... \n"
                        + "     Retrieving List! :)");
                Scanner sc = new Scanner(data);
                while (sc.hasNext()) {
                    String[] content = sc.nextLine().split("\\|");
                    String command = content[0];
                    String status = content[1];
                    Task task;
                    if (command.equals("T")) {
                        task = new Todo(status, content[2]);
                    } else {
                        String[] description = content[2].split("@");
                        if (command.equals("D")) {
                            task = new Deadline(status, description);
                        } else {
                            task = new Event(status, description);
                        }
                    }
                    list.addTask(task);
                }
            }
        } catch (DukeException | IOException ex) {
            Ui.printBox(ex.getMessage());
        }
        return list;
    }

    /**
     * Save the task in the specified <code>list</code> into the file.
     *
     * @param list tasks saved
     * @throws IOException IOException if file not found
     */
    public String save(TaskList list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(data));
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus();
            String type = task.getType();
            String description = task.getDetails();
            assert type.equals("T") || type.equals("D") || type.equals("E") : "Error! Not event,deadline or todo task";
            // check whether the saved data is a todo
            if (type.equals("T")) {
                writer.write(type + "|" + status + "|" + description);
            } else {
                // Check whether the saved data is a deadline or event
                String date = type.equals("D") ? ((Deadline) task).getDate() : ((Event) task).getDate();
                String time;
                if (type.equals("D")) {
                    time = ((Deadline) task).getTime();
                } else {
                    time = ((Event) task).getTime();
                }
                if (time == null ) {
                    writer.write(type + "|" + status + "|" + description + "@" + date);
                } else {
                    writer.write(type + "|" + status + "|" + description + "@" + date + "@" + time);
                }
            }
            writer.newLine();
        }
        writer.close();
        return "Task List Saved Successfully!\n"
                + Ui.EXIT_MSG;
    }
}
