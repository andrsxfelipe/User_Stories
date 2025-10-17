package ui;

import javax.swing.*;

public class UIHelper {
    public static String input(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    public static void show(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showError(String message, String error){
        JOptionPane.showMessageDialog(null, message, error, JOptionPane.ERROR_MESSAGE);
    }

    public static int confirm(String title, Object[] fields) {
        return JOptionPane.showConfirmDialog(
                null, fields, title, JOptionPane.OK_CANCEL_OPTION
        );
    }

    public static int menu(String title, String[] options) {
        return JOptionPane.showOptionDialog(
                null, "¿Qué deseas hacer?", title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]
        );
    }
}
