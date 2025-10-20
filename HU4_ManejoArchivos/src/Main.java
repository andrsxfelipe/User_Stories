import service.UserService;

import javax.swing.*;

public static void main(String[] args){
    try {
        int agregar = JOptionPane.showConfirmDialog(null, "Agregar usuario", null, JOptionPane.YES_NO_OPTION);
        while (agregar == 0) {

            JTextField nameField = new JTextField();
            JTextField phoneField = new JTextField();
            JTextField birthField = new JTextField();

            Object[] fields = {
                    "Nombre:", nameField,
                    "Telefono:", phoneField,
                    "Año de nacimiento:", birthField
            };

            int ok = JOptionPane.showConfirmDialog(null, fields, "Agregar usuario", JOptionPane.OK_CANCEL_OPTION);
            if (ok == 0) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String birth = birthField.getText();
                if (name.isEmpty() || phone.isEmpty() || birth.isEmpty()) {
                    throw new IllegalArgumentException("Por favor completa todos los campos.");
                }
                UserService.addUser(name,phone,birth);

                JOptionPane.showMessageDialog(null,"Usuario agregado existosamente");
            }
            agregar = JOptionPane.showConfirmDialog(null, "Agregar usuario", null, JOptionPane.YES_NO_OPTION);
        }
    } catch (IllegalArgumentException e){
        JOptionPane.showMessageDialog(null,e.getMessage());
    }
}
