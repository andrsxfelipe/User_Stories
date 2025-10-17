package dao;

import model.Producto;
import ui.UIHelper;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public void insertar(Producto p){
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3,p.getStock());

            stmt.executeUpdate();
            UIHelper.show("Producto agregado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> listar(){
        List<Producto> productos = new ArrayList<>();
        String sql ="SELECT * FROM productos";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next()){
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productos;
    }

    public void actualizarPrecio(int id, Double newPrice) {
        String sql = "UPDATE productos SET precio=? WHERE id=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                UIHelper.show("Telefono actualizado.");
            } else {
                UIHelper.show("No se encontró algún producto con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarStock(int id, int newStock){
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,newStock);
            stmt.setInt(2,id);

            int filas = stmt.executeUpdate();

            if (filas > 0){
                UIHelper.show("Stock actualizado");
            } else {
                UIHelper.show("No se encontró algún producto con el ID proporcionado.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void eliminar(int id){
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();

            if (filas > 0){
                UIHelper.show("Producto eliminado.");
            } else {
                UIHelper.show("No se encontró algún producto con el ID proporcionado.");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Producto> buscarProducto(String product) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // asignar el valor al ?
            stmt.setString(1, "%" + product.toLowerCase() + "%");

            // ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }



}
