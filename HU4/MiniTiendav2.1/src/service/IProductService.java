package service;

import model.Producto;

import java.util.List;

public interface IProductService {
    void agregarProducto();
    void actualizar();
    void eliminarProducto();
    void buscarPorNombre();
    void searchById();
}
