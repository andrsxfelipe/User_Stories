package util;

import model.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserFileWriter {
    private static final String FILE_PATH = "usuarios.txt";
    private static boolean sessionStarted = false;

    public static void saveUser(Usuario usuario) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {

            if (!sessionStarted) {
                String fechaHora = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                writer.println();
                writer.println("=== Sesión iniciada el " + fechaHora + " ===");
                sessionStarted = true;
            }

            writer.printf("Nombre: %s | Teléfono: %s | Cumpleaños: %s%n",
                    usuario.getName(),
                    usuario.getPhone(),
                    usuario.getBirth().toString());
        } catch (IOException e) {
            System.err.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
}
