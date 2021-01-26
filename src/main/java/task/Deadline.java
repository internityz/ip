package task;

import simulator.DukeException;

public class Deadline extends Task {
    protected StringBuilder date = new StringBuilder();

    public String getDate() {
        return date.toString();
    }

    public Deadline(String[] input) throws DukeException {
        this.type = "D";
        if (input.length != 0) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/by")) {
                    for (int j = i; j < input.length - 2; j++) {
                        this.date.append(input[j + 1]);
                        this.date.append(" ");
                    }
                    this.date.append(input[input.length - 1]);
                    break;
                } else {
                    this.description.append(input[i]);
                    this.description.append(" ");
                }
            }
        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }
    }

    public Deadline(String status, String[] input) throws DukeException {
        this.type = "D";
        this.isDone = status.equals("complete");
        if (input.length != 0) {
            if (input.length != 2) {
                throw new DukeException("☹ OOPS!!! Date of todo cannot be empty, please check!");
            } else {
                this.description.append(input[0]);
                this.date.append(input[1]);
            }
        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                + this.date.toString() + ")";
    }
}
