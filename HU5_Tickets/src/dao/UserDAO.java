package dao;

import config.DbConfig;
import domain.Role;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public boolean toInsert(User u){
        String sql = "INSERT INTO usuarios (nombre, rol_id) VALUES (?,?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,u.getName());
            stmt.setInt(2,u.getRole().getId());

            int rows = stmt.executeUpdate();
            return (rows>0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> toList(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.id AS id, u.nombre AS nombre, r.nombre AS rol, r.id AS rol_id FROM usuarios u JOIN roles r ON u.rol_id = r.id;";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        new Role(rs.getInt("rol_id"),rs.getString("rol"))
                ));
            }
            return users;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
