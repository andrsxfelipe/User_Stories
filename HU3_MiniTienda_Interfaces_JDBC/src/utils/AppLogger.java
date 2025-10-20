package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.*;

public class AppLogger {
    private static final Logger logger = Logger.getLogger("AppLogger");
    private static final List<String> sessionLogs = new ArrayList<>();

    static {
        try {
            // Crear carpeta de logs si no existe
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Archivo de log con fecha actual
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String logFilePath = "logs/app_" + date + ".log";

            // Configurar el FileHandler (modo append = true)
            Handler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new Formatter() {
                private static final String format = "[%1$tF %1$tT] [%2$s] %3$s %n";

                @Override
                public synchronized String format(LogRecord record) {
                    return String.format(format,
                            new Date(record.getMillis()),
                            record.getLevel().getLocalizedName(),
                            record.getMessage());
                }
            });

            // Configurar salida en consola
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());

            // Registrar handlers
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);

            // Evitar logs duplicados del handler padre
            logger.setUseParentHandlers(false);

            // Nivel de logs: INFO, WARNING, SEVERE, etc.
            logger.setLevel(Level.ALL);

            logger.info("=== Logger inicializado correctamente ===");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("⚠ No se pudo inicializar el logger: " + e.getMessage());
        }
    }

    // --- Métodos públicos de utilidad ---
    public static void logInfo(String message) {
        sessionLogs.add("[INFO] " + message);
        logger.info(message);
    }

    public static void logWarning(String message) {
        sessionLogs.add("[AVISO] "+ message);
        logger.warning(message);
    }

    public static void logError(String message, Throwable ex) {
        sessionLogs.add("[ERROR] " + message + " (" + ex.getMessage() + ")");
        logger.log(Level.SEVERE, message, ex);
    }

    public static void logDebug(String message) {
        logger.fine(message);
    }

    public static String getSessionSummary() {
        if (sessionLogs.isEmpty()) return "No se registraron eventos en esta sesión.";
        StringBuilder sb = new StringBuilder("📋 Resumen de la sesión:\n\n");
        for (String log : sessionLogs) {
            sb.append(log).append("\n");
        }
        return sb.toString();
    }
}
