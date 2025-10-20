package utils;

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UIHelper {
    public static Map<String, String> mostrarForm(String titulo, String... campos) {
        Map<String, JTextField> entradasList = new LinkedHashMap<>();
        Object[] componentes = new Object[campos.length * 2];
        for (int i = 0; i < campos.length; i++) {
            String etiqueta = campos[i];
            JTextField campoTexto = new JTextField();

            entradasList.put(etiqueta, campoTexto);
            componentes[i * 2] = campos[i]+":";
            componentes[(i * 2) + 1] = campoTexto;
        }

        int eleccion = JOptionPane.showConfirmDialog(
                null,
                componentes,
                titulo,
                JOptionPane.OK_CANCEL_OPTION
        );

        if (eleccion == JOptionPane.OK_OPTION) {
            return entradasList.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().getText()
                    ));
        }
        return Collections.emptyMap();
    }

    public static int elegirOpcion(String title, String... opciones){
        return JOptionPane.showOptionDialog(
                null, title, null,
                JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]
        );
    }

    public static String ingresar(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }

    public static void mostrar(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void mostrar(StringBuilder mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
