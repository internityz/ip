package task;

import simulator.DukeException;

import static simulator.Design.printBox;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String type = "D";
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String[] input) throws DukeException {
        super(input);
        if (input.length != 0) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/by")) {
                    try {
                        this.date = LocalDate.parse(input[i + 1]);
                        this.time = LocalTime.parse(input[i + 2]);
                        break;
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        if (this.time == null) {
                            printBox("WARNING! No Time Detected!");
                        } else if (this.date == null) {
                            throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
                        } else {
                            throw new DukeException("☹ OOPS!!! Date & Time of Deadline cannot be empty, please check!");
                        }
                    }
                } else {
                    this.description.append(input[i]);
                    this.description.append(" ");
                }
            }
        } else {
            throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
        }
    }


    @Override
    public String toString() {
        if (this.time == null) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                    + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                    + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        }
    }
}
