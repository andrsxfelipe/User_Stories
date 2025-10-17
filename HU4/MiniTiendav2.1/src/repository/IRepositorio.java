package repository;

import model.Producto;

public interface IRepositorio<T> {
    void crear(T t);
    T buscarPorId(int id);
    void actualizar(Producto p);
    void eliminar(int id);
}
