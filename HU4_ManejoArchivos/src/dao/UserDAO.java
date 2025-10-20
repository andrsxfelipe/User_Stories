package dao;

import config.ConfigDB;
import exception.DataAccessException;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public static void addUser(Usuario usuario){
        String sql = "INSERT INTO users(name,phone,birthday) VALUES (?,?,?)";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,usuario.getName());
            stmt.setString(2,usuario.getPhone());
            stmt.setObject(3,usuario.getBirth());

            stmt.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DataAccessException("Error al insertar el usuario",e);
        }
    }
}
