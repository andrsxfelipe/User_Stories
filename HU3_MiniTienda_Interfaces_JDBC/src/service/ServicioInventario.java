package service;

import exception.NoProductFoundException;
import exception.NoProductsFoundException;
import model.Producto;
import repository.Repositorio;

import java.util.List;

public class ServicioInventario implements IServicioInventario{

    private static final Repositorio repo = new Repositorio();

    @Override
    public void agregarProducto(String n, String p, String c) {
        try{
            boolean existe = repo.checkExistencia(n);
            if (existe){
                throw new IllegalArgumentException("El producto que intenta agrega ya existe!");
            } else {
                if (n.isEmpty() || p.isEmpty() || c.isEmpty()) throw new IllegalArgumentException("Llena todos los campos");

                double precio = Double.parseDouble(p);
                int cantidad = Integer.parseInt(c);

                if (precio<0 || cantidad<0)
                    throw new IllegalArgumentException("El precio y la cantidad deben ser enteros positivos válidos");

                repo.crear(new Producto(n, precio, cantidad));
            }
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El precio y cantidad deben ser valores válidos");
        }
    }

    @Override
    public void actualizarPrecio(String idStr, String precioStr) {
        try {

            if (idStr.isEmpty() || precioStr.isEmpty()) throw new IllegalArgumentException("Ingresa todos los datos");

            int id = Integer.parseInt(idStr);
            double precio = Double.parseDouble(precioStr);

            if (id<=0) throw new IllegalArgumentException("El ID debe ser un número mayor de 0");
            if (precio<=0) throw new IllegalArgumentException("El precio debe ser mayor que 0");

            boolean actualizado = repo.actualizar(new Producto(id,precio));
            if (!actualizado) throw new NoProductFoundException("No se encontró un producto con este ID");

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Los valores deben ser dígitos");
        }
    }

    @Override
    public void actualizarStock(String idStr, String stockStr) {
        try {

            if (idStr.isEmpty() || stockStr.isEmpty()) throw new IllegalArgumentException("Ingresa todos los datos");

            int id = Integer.parseInt(idStr);
            int stock = Integer.parseInt(stockStr);

            if (id<=0) throw new IllegalArgumentException("El ID debe ser un número mayor de 0");
            if (stock<=0) throw new IllegalArgumentException("El stock debe ser mayor que 0");
            Producto producto = new Producto(id,stock);

            boolean actualizado = repo.actualizar(producto);
            if (!actualizado) throw new NoProductFoundException("No se encontró un producto con este ID");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Los valores deben ser dígitos");
        }
    }

    @Override
    public void eliminarProducto(String idStr) {
        try{
            if (idStr.isEmpty()) throw new IllegalArgumentException("Ingresa un ID!");

            int id = Integer.parseInt(idStr);

            boolean eliminado = repo.eliminar(id);
            if (!eliminado) throw new NoProductFoundException("No se encontró un producto con este ID");
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El ID debe ser un dígito valido");
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String producto) {
        if (producto.isEmpty()) throw new IllegalArgumentException("Ingresa el producto a buscar");
        List<Producto> encontrados = repo.buscar(producto);
        if (encontrados.isEmpty()) throw new NoProductsFoundException("No se encontraron productos");
        return encontrados;
    }

    public List<Producto> buscarTodos(){
        List<Producto> catalogo = repo.buscarTodos();
        if (catalogo.isEmpty()) throw new NoProductsFoundException("No se encontraron productos");
        return catalogo;
    }
}
