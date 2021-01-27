package ui;

import task.TaskList;

public class Ui {
    public final static String MARK_MSG = "You have marked this task as completed: \n";
    public final static String ADD_MSG = "Roger that! Added the following task: \n \n      ";
    public final static String DELETE_MSG = "Roger that! Deleted the follow task: \n \n      ";
    public final static String indent4 = "     ";
    public final static String TOP_BORDER = "\n    ********************************* \n";
    public final static String BOTTOM_BORDER = "    ********************************* \n";

    public final static String WELCOME_MSG = "    █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ \n"
            + "         █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█ \n"
            + "         █░░║║║╠─║─║─║║║║║╠─░░█ \n"
            + "         █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█ \n"
            + "         █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n";
    public final static String LOGO = "         ──────▄▀▄─────▄▀▄ \n"
            + "         ─────▄█░░▀▀▀▀▀░░█▄ \n"
            + "         ─▄▄──█░░░░░░░░░░░█──▄▄ \n"
            + "         █▄▄█─█░░▀░░┬░░▀░░█─█▄▄█ ";
    public final static String GREETING_MSG = "Hey there! I'm Kawaii Kat \n"
            + "     How can i assist you ~nya~?";
    public final static String EXIT_MSG = "Farewell. See you soon :)!";

    public static void printBox(String input) {
        System.out.println(TOP_BORDER + indent4 + input
                + "\n" + BOTTOM_BORDER);
    }

    public static void printList(TaskList list) {
        System.out.print(TOP_BORDER);
        System.out.println("     This is your present task list: \n");
        for (int i = 1; i <= list.size(); i++) {
            if (i == list.size()) {
                System.out.println(indent4 + i + ") "
                        + list.get(i - 1));
            } else {
                System.out.println(indent4 + i + ") "
                        + list.get(i - 1));
            }
        }
        System.out.println(BOTTOM_BORDER);
    }
}
