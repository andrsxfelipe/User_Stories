package repository;

public interface IRepositorio <T>{

    void crear(T t);
    void buscarPorId(int id);
    void buscarTodos();
    void actualizar(T t);
    void eliminar(int id);

}
