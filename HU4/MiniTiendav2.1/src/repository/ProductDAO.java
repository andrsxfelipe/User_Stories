package repository;

import model.Producto;
import ui.UIHelper;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IRepositorio<Producto>{

    @Override
    public void crear(Producto p) {
        String sql = "INSERT INTO productos (nombre,precio,stock) VALUES (?,?,?)";
        try(Connection conn = ConexionBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,p.getNombre());
            stmt.setDouble(2,p.getPrecio());
            stmt.setInt(3,p.getStock());

            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarPorId(int id) {
        String sql ="SELECT * FROM productos WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Producto p) {
        String sql = "UPDATE productos SET precio = ? , stock = ? WHERE id = ? ";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setDouble(1, p.getPrecio());
            stmt.setInt(2, p.getStock());
            stmt.setInt(3, p.getId());

            int cambios = stmt.executeUpdate();
            if (cambios>0) {
                UIHelper.show("Producto actualizado!");
            }
            else {
                UIHelper.showError("ID no encontrado.","Entrada no válida");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, id);

            int eliminados = stmt.executeUpdate();

            if (eliminados>0){
                UIHelper.show("Producto eliminado exitosamente!");
            } else {
                UIHelper.showError("Ingrese un id válido.", "Producto no encontrado");
            }

        } catch (SQLException e){
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

    public List<Producto> buscarProducto(String product) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + product.toLowerCase() + "%");

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


































