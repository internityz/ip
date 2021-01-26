package task;

import simulator.DukeException;

import static simulator.Design.printBox;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public String getTime() {
        if (this.time != null) {
            return this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } else {
            return null;
        }
    }

    public Deadline(String[] input) throws DukeException {
        this.type = "D";
        if (input.length != 0) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/by")) {
                    try {
                        this.date = LocalDate.parse(input[i + 1]);
                        this.time = LocalTime.parse(input[i + 2]);

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        if (this.time == null) {
                            printBox("WARNING! No Time Detected!");
                        } else if (this.date == null) {
                            throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
                        } else {
                            throw new DukeException("☹ OOPS!!! Date & Time of Deadline cannot be empty, please check!");
                        }
                    }
                    break;
                } else {
                    this.description.append(input[i]);
                    this.description.append(" ");
                }
            }
        } else {
            throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
        }
    }

    public Deadline(String status, String[] input) throws DukeException {
        this.type = "D";
        this.isDone = status.equals("complete");
        try {
            this.description.append(input[0]);
            this.date = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.time = LocalTime.parse(input[2], DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (ArrayIndexOutOfBoundsException ex) {
            if (this.time == null) {
                printBox("WARNING!!! \n"
                        + "     Deadline with no Timing Specified Detected!");
            } else if (this.date == null) {
                throw new DukeException("☹ OOPS!!! Date of Deadline cannot be empty, please check!");
            } else {
                throw new DukeException("☹ OOPS!!! Description of Deadline cannot be empty, please check!");
            }
        }
    }

    @Override
    public String toString() {
        if (this.time == null) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                    + this.getDate() + ")";
        } else {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                    + this.getDate() + " " + this.getTime() + ")";
        }
    }
}
