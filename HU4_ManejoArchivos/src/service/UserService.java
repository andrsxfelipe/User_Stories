package service;

import dao.UserDAO;
import model.Usuario;
import util.UserFileWriter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserService {
    public static void addUser(String n, String p, String b){
        try {
            if (n.isEmpty() || p.isEmpty() || b.isEmpty())
                throw new IllegalArgumentException("Ingrese todos los campos");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

            LocalDate birthday = LocalDate.parse(b, formatter);

            if (birthday.isAfter(LocalDate.now()))
                throw new IllegalArgumentException("El usuario debe haber nacido para registrarlo");

            Usuario usuario = new Usuario(n,p,birthday);

            UserDAO.addUser(usuario);

            UserFileWriter.saveUser(usuario);

        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Ingrese un fecha válida ej: 30/01/2002");
        }
    }
}
