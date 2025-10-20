package dao;

import config.DbConfig;
import domain.Category;
import domain.Status;
import domain.Ticket;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public boolean createTicket(Ticket t){
        String sql = "INSERT INTO tickets (titulo, descripcion, reporter_id, categoria_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1, t.getTitle());
            stmt.setString(2, t.getDescription());
            stmt.setInt(3, t.getReporter().getId());
            stmt.setInt(4, t.getCategory().getId());

            int rows = stmt.executeUpdate();
            return (rows>0);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean assignTicket(int[] assignation){
        String sql = "UPDATE tickets SET assignee_id = ? WHERE id = ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,assignation[1]);
            stmt.setInt(2,assignation[0]);

            int rows = stmt.executeUpdate();
            return (rows>0);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeTicketStatus(int t){
        String sql = "UPDATE tickets SET estado_id = estado_id + 1 WHERE id = ? AND estado_id < 3";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, t);

            int rows = stmt.executeUpdate();
            return rows>0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Ticket> searchByCatStat(int [] s) {
        String sql = "SELECT t.titulo, t.descripcion, rep.nombre AS reporter, asi.nombre AS asignado, " +
                "c.nombre AS categoria, e.nombre  AS estado, t.fecha_creacion " +
                "FROM tickets t " +
                "JOIN usuarios rep ON rep.id = t.reporter_id " +
                "LEFT JOIN usuarios asi ON asi.id  = t.assignee_id " +
                "JOIN categorias c ON c.id = t.categoria_id " +
                "JOIN estados e ON e.id  = t.estado_id WHERE t.categoria_id = ? AND t.estado_id = ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, s[0]);
            stmt.setInt(2, s[1]);

            ResultSet rs = stmt.executeQuery();

            List<Ticket> ticketsFound = new ArrayList<>();

            while (rs.next()) {
                ticketsFound.add(new Ticket(
                        rs.getString("titulo"), rs.getString("descripcion"),
                        new User(rs.getString("reporter"), null),
                        new User(rs.getString("asignado"), null),
                        new Category(rs.getString("categoria")),
                        new Status(rs.getString("estado"))
                ));
            }

            return ticketsFound;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ticket> searchByAsignee(String a) {
        String sql = "SELECT t.titulo, t.descripcion, rep.nombre AS reporter, asi.nombre AS asignado, " +
                "c.nombre AS categoria, e.nombre  AS estado, t.fecha_creacion " +
                "FROM tickets t " +
                "JOIN usuarios rep ON rep.id = t.reporter_id " +
                "LEFT JOIN usuarios asi ON asi.id  = t.assignee_id " +
                "JOIN categorias c ON c.id = t.categoria_id " +
                "JOIN estados e ON e.id  = t.estado_id WHERE asi.nombre LIKE ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, "%" + a + "%");

            ResultSet rs = stmt.executeQuery();

            List<Ticket> ticketsFound = new ArrayList<>();

            while (rs.next()) {
                ticketsFound.add(new Ticket(
                        rs.getString("titulo"), rs.getString("descripcion"),
                        new User(rs.getString("reporter"), null),
                        new User(rs.getString("asignado"), null),
                        new Category(rs.getString("categoria")),
                        new Status(rs.getString("estado"))
                ));
            }

            return ticketsFound;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public List<Category> topThree() {
        String sql = "SELECT c.nombre, COUNT(*) as total " +
                "FROM tickets t " +
                "JOIN categorias c ON t.categoria_id = c.id " +
                "GROUP BY c.nombre " +
                "ORDER BY total DESC " +
                "LIMIT 3";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();

            List<Category> categories = new ArrayList<>();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getString("nombre"),
                        rs.getInt("total")
                ));
            }

            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
