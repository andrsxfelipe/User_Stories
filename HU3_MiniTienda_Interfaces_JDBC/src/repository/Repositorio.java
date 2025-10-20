package repository;

import Config.ConfigDB;
import exception.DataAccessException;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repositorio implements IRepositorio<Producto> {

    public boolean checkExistencia(String pducto){
        String sql = "SELECT COUNT(*) AS cuenta FROM productos WHERE LOWER(nombre) = ?";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,pducto);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getInt("cuenta")>0;
            } else {
                return false;
            }
        } catch (SQLException e){
            throw new DataAccessException("Ha ocurrido un error verificando si el producto ya existe",e);
        }
    }

    @Override
    public void crear(Producto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?,?,?)";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,p.getNombre());
            stmt.setDouble(2,p.getPrecio());
            stmt.setInt(3,p.getStock());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error al crear el producto",e);
        }
    }

    @Override
    public Producto buscarPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            } else {
                return null;
            }
        } catch (SQLException e){
            throw new DataAccessException("Hubo un error al intentar buscar",e);
        }
    }

    public List<Producto> buscar(String busqueda){
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ?";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,"%"+busqueda+"%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                productos.add( new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }

            return productos;

        } catch (SQLException e){
            throw new DataAccessException("Error al consultar la búsqueda",e);
        }
    }

    @Override
    public List<Producto> buscarTodos() {
        String sql = "SELECT * FROM productos";
        List<Producto> pductos = new ArrayList<>();
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pductos.add( new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                        )
                );
            }
            return pductos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DataAccessException("Error al consultar los productos", e);
        }
    }

    @Override
    public boolean actualizar(Producto p) {
        if ((p.getPrecio() == 0 && p.getStock() == 0) || p.getId() == 0)
            throw new IllegalArgumentException("No se proporcionó suficiente información");
        String sql;
        double precio = p.getPrecio();
        int stock = p.getStock();
        if (precio > 0){
            sql = "UPDATE productos SET precio = ? WHERE id = ?";
        } else{
            sql = "UPDATE productos SET stock = ? WHERE id = ?";

        }

        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            if (precio > 0) {
                stmt.setDouble(1,p.getPrecio());
            } else if (stock > 0) {
                stmt.setInt(1, p.getStock());
            }
            stmt.setInt(2,p.getId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
            throw new DataAccessException("Hubo un error actualizando los datos",e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
            throw new DataAccessException("Error al eliminar el registo",e);
        }
    }
}
