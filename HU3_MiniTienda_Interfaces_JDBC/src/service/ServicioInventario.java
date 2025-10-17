package service;

import model.Producto;
import repository.Repositorio;

public class ServicioInventario implements IServicioInventario{

    private static final Repositorio repo = new Repositorio();

    @Override
    public void agregarProducto(String n, String p, String c) {
        try{
            if (n.isEmpty() || p.isEmpty() || c.isEmpty()) throw new IllegalArgumentException("Llena todos los campos");

            double precio = Double.parseDouble(p);
            int cantidad = Integer.parseInt(c);

            if (precio<0 || cantidad<0)
                throw new IllegalArgumentException("El precio y la cantidad deben ser enteros positivos válidos");

            repo.crear(new Producto(n, precio, cantidad));

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

            if (id<0) throw new IllegalArgumentException("El ID debe ser un número mayor de 0");
            if (precio<0) throw new IllegalArgumentException("El precio debe ser mayor que 0");

            repo.actualizar(new Producto(id,precio));

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Los valores deben ser dígitos");
        }
    }

    @Override
    public void actualizarStock(String idStr, String stockStr) {
        try {

            if (idStr.isEmpty() || stockStr.isEmpty()) throw new IllegalArgumentException("Ingresa todos los datos");

            int id = Integer.parseInt(idStr);
            int stock = Integer.parseInt(idStr);

            if (id<0) throw new IllegalArgumentException("El ID debe ser un número mayor de 0");
            if (stock<0) throw new IllegalArgumentException("El precio debe ser mayor que 0");

            repo.actualizar(new Producto(id,stock));

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Los valores deben ser dígitos");
        }
    }

    @Override
    public void eliminarProducto(String idStr) {
        try{
            if (idStr.isEmpty()) throw new IllegalArgumentException("Ingresa un ID!");

            int id = Integer.parseInt(idStr);

            repo.eliminar(id);

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El ID debe ser un dígito valido");
        }
    }

    @Override
    public void buscarPorNombre(String producto) {

    }
}
