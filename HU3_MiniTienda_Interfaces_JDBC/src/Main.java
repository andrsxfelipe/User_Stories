import exception.DataAccessException;
import exception.NoProductFoundException;
import exception.NoProductsFoundException;
import model.Producto;
import service.ServicioInventario;
import utils.InputValidator;
import utils.UIHelper;

import java.util.List;
import java.util.Map;


public class Main {

    private static final ServicioInventario inventario = new ServicioInventario();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            int eleccion = UIHelper.elegirOpcion(
                    "¿Qué desea hacer?","Agregar producto","Listar inventario","Actualizar precio",
                    "Actualizar stock","Eliminar producto","Buscar producto por nombre","Salir"
            );
            try {
                switch (eleccion) {
                    case 0 -> agregarProducto();
                    case 1 -> buscarTodos();
                    case 2 -> actualizarPrecio();
                    case 3 -> actualizarStock();
                    case 4 -> eliminarProducto();
                    case 5 -> buscarPorNombre();
                    default -> exit = true;
                }
            } catch (NoProductsFoundException | IllegalArgumentException | DataAccessException |
            NoProductFoundException e) {
                UIHelper.mostrar(e.getMessage());
            } catch (Exception e) {
                UIHelper.mostrar("Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }
    }

    public static void agregarProducto(){
        Map<String,String> infoLibro = UIHelper.mostrarForm("Agregar","Nombre","Precio","Cantidad");
        if (!infoLibro.isEmpty()) {
            String nombre = InputValidator.capitalizar(infoLibro.get("Nombre").trim());
            String precio = infoLibro.get("Precio").trim();
            String cantidad = infoLibro.get("Cantidad").trim();

            if (nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty())
                throw new IllegalArgumentException("Llena todos los campos.");

            inventario.agregarProducto(nombre, precio, cantidad);
            UIHelper.mostrar("Producto agregado exitosamente.");
        }
    }

    public static void buscarTodos(){
        List<Producto> catalogo = inventario.buscarTodos();
        StringBuilder productos = new StringBuilder();
        for (Producto p : catalogo){
            productos.append(p.getNombre()).append("(").append(p.getId()).append(")")
                    .append(":\n Precio: ").append(p.getPrecio())
                    .append(" | Stock: ").append(p.getStock()).append("\n\n");
        }
        UIHelper.mostrar(productos);
    }

    public static void actualizarPrecio(){
        Map<String, String> precioInfo =
                UIHelper.mostrarForm("Actualizar precio","ID Producto","Nuevo Precio");
        if (!precioInfo.isEmpty()) {
            String id = precioInfo.get("ID Producto").trim();
            String nuevoPrecio = precioInfo.get("Nuevo Precio").trim();

            if ( id.isEmpty() || nuevoPrecio.isEmpty()){
                throw new IllegalArgumentException("Completa todos los campos");}

            inventario.actualizarPrecio(id, nuevoPrecio);
            UIHelper.mostrar("Precio actualizado existosamente.");
        }
    }

    public static void actualizarStock() {
        Map<String, String> stockInfo =
                UIHelper.mostrarForm("Actualizar stock","ID Producto","Nuevo Stock");
        if (!stockInfo.isEmpty()) {
            String id = stockInfo.get("ID Producto").trim();
            String nuevoStock = stockInfo.get("Nuevo Stock").trim();

            if (id.isEmpty() || nuevoStock.isEmpty())
                throw new IllegalArgumentException("Completa todos los campos");

            inventario.actualizarStock(id, nuevoStock);
            UIHelper.mostrar("Stock actualizado existosamente");
        }
    }

    public static void eliminarProducto() {
        String id = UIHelper.ingresar("Ingrese el ID del producto a eliminar").trim();
        if (id.isEmpty()) throw new IllegalArgumentException("Porfavor, proporciona un id");
        inventario.eliminarProducto(id);
        UIHelper.mostrar("Producto eliminado exitosamente.");
    }

    public static void buscarPorNombre() {
        String buscar = UIHelper.ingresar("¿Qué producto busca?").trim();
        if (buscar.isEmpty()) throw new IllegalArgumentException("Ingresa un producto");
        List<Producto> encontradosList = inventario.buscarPorNombre(buscar);
        StringBuilder encontrados = new StringBuilder();
        for (Producto p : encontradosList){
            encontrados.append(p.getNombre()).append("(").append(p.getId()).append(")")
                    .append(":\n Precio: ").append(p.getPrecio())
                    .append(" | Stock: ").append(p.getStock()).append("\n\n");
        }
        UIHelper.mostrar(encontrados);
    }
}