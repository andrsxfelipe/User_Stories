package service;

import model.Producto;

import java.util.List;

public interface IServicioInventario {
    void agregarProducto(String n, String p, String c);
    void actualizarPrecio(String idStr, String precioStr);
    void actualizarStock(String idStr, String stockStr);
    void eliminarProducto(String id);
    List<Producto> buscarPorNombre(String producto);
}
