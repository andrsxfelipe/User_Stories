package service;

public interface IServicioInventario {
    void agregarProducto(String n, String p, String c);
    void actualizarPrecio(String idStr, String precioStr);
    void actualizarStock(String idStr, String stockStr);
    void eliminarProducto(String id);
    void buscarPorNombre(String producto);
}
