package repository;

import java.util.List;

public interface IRepositorio <T>{

    void crear(T t);
    T buscarPorId(int id);
    List<T> buscarTodos();
    boolean actualizar(T t);
    boolean eliminar(int id);

}
