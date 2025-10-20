import exception.DataAccessException;
import exception.NoProductFoundException;
import exception.NoProductsFoundException;
import model.Producto;
import service.ServicioInventario;
import utils.AppLogger;
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
                    default -> exit = salir();
                }
            } catch (Exception e) {
                AppLogger.logError("Error inesperado en el menú principal",e);
                UIHelper.mostrar("Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }
    }

    public static void agregarProducto() {
        try {
            Map<String, String> infoLibro = UIHelper.mostrarForm("Agregar", "Nombre", "Precio", "Cantidad");
            if (!infoLibro.isEmpty()) {
                String nombre = InputValidator.capitalizar(infoLibro.get("Nombre").trim());
                String precio = infoLibro.get("Precio").trim();
                String cantidad = infoLibro.get("Cantidad").trim();

                if (nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty())
                    throw new IllegalArgumentException("Llena todos los campos.");

                inventario.agregarProducto(nombre, precio, cantidad);
                AppLogger.logInfo("Producto agregado: "+nombre);
                UIHelper.mostrar("Producto agregado exitosamente.");
            }
        } catch (IllegalArgumentException | DataAccessException e) {
            AppLogger.logWarning("Intento de agregar el producto: "+e.getMessage());
            UIHelper.mostrar(e.getMessage());
        } catch (Exception e){
            AppLogger.logError("Error inesperado al agregar el producto",e);
        }
    }

    public static void buscarTodos() {
        try {
            List<Producto> catalogo = inventario.buscarTodos();
            StringBuilder productos = new StringBuilder();
            for (Producto p : catalogo) {
                productos.append(p.getNombre()).append("(").append(p.getId()).append(")")
                        .append(":\n Precio: ").append(p.getPrecio())
                        .append(" | Stock: ").append(p.getStock()).append("\n\n");
            }
            AppLogger.logInfo("Listado de productos mostrado");
            UIHelper.mostrar(productos);
        } catch (NoProductsFoundException | DataAccessException e){
            AppLogger.logWarning("Intento de listar todos los productos: "+e.getMessage());
            UIHelper.mostrar(e.getMessage());
        } catch (Exception e){
            AppLogger.logError("Error inesperado al buscar los productos",e);
        }
    }

    public static void actualizarPrecio() {
        try {
            Map<String, String> precioInfo =
                    UIHelper.mostrarForm("Actualizar precio", "ID Producto", "Nuevo Precio");
            if (!precioInfo.isEmpty()) {
                String id = precioInfo.get("ID Producto").trim();
                String nuevoPrecio = precioInfo.get("Nuevo Precio").trim();

                if (id.isEmpty() || nuevoPrecio.isEmpty()) {
                    throw new IllegalArgumentException("Completa todos los campos");
                }

                inventario.actualizarPrecio(id, nuevoPrecio);
                AppLogger.logInfo("Precio del producto con id "+id+" actualizado.");
                UIHelper.mostrar("Precio actualizado existosamente.");
            }
        } catch (IllegalArgumentException | NoProductFoundException | DataAccessException e){
            AppLogger.logWarning("Intento de intentar actualizar el precio: "+e.getMessage());
            UIHelper.mostrar(e.getMessage());
        } catch (Exception e){
            AppLogger.logError("Error inesperado al actualizar el precio",e);
        }
    }

    public static void actualizarStock() {
        try {
            Map<String, String> stockInfo =
                    UIHelper.mostrarForm("Actualizar stock", "ID Producto", "Nuevo Stock");
            if (!stockInfo.isEmpty()) {
                String id = stockInfo.get("ID Producto").trim();
                String nuevoStock = stockInfo.get("Nuevo Stock").trim();

                if (id.isEmpty() || nuevoStock.isEmpty())
                    throw new IllegalArgumentException("Completa todos los campos");

                inventario.actualizarStock(id, nuevoStock);
                AppLogger.logInfo("Producto con id "+id+" actualizado");
                UIHelper.mostrar("Stock actualizado existosamente");
            }
        } catch (IllegalArgumentException | NoProductFoundException | DataAccessException e){
            UIHelper.mostrar(e.getMessage());
            AppLogger.logWarning("Intento de actualizar el stock: "+e.getMessage());
        } catch (Exception e){
            AppLogger.logError("Error inesperado al actualizar el stock",e);
        }
    }

    public static void eliminarProducto() {
        try {
            String id = UIHelper.ingresar("Ingrese el ID del producto a eliminar").trim();
            if (id.isEmpty()) throw new IllegalArgumentException("Porfavor, proporciona un id");
            inventario.eliminarProducto(id);
            AppLogger.logInfo("Producto con id "+id+" eliminado");
            UIHelper.mostrar("Producto eliminado exitosamente.");
        } catch (IllegalArgumentException | NoProductFoundException | DataAccessException e){
            UIHelper.mostrar(e.getMessage());
            AppLogger.logWarning("Intento de eliminar producto: "+e.getMessage());
        } catch (Exception e){
            AppLogger.logError("Error inesperado al intentar eliminar el producto",e);
        }
    }

    public static void buscarPorNombre() {
        try {
            String buscar = UIHelper.ingresar("¿Qué producto busca?").trim();
            if (buscar.isEmpty()) throw new IllegalArgumentException("Ingresa un producto");
            List<Producto> encontradosList = inventario.buscarPorNombre(buscar);
            StringBuilder encontrados = new StringBuilder();
            for (Producto p : encontradosList) {
                encontrados.append(p.getNombre()).append("(").append(p.getId()).append(")")
                        .append(":\n Precio: ").append(p.getPrecio())
                        .append(" | Stock: ").append(p.getStock()).append("\n\n");
            }
            AppLogger.logInfo("Productos con el nombre '"+buscar+"' encontrados");
            UIHelper.mostrar(encontrados);
        } catch (NoProductsFoundException | IllegalArgumentException | DataAccessException e){
            UIHelper.mostrar(e.getMessage());
            AppLogger.logWarning("Intento de buscar un producto: "+e.getMessage());
        } catch (Exception e){
            AppLogger.logError("Error inesperado al buscar un producto",e);
        }
    }

    public static boolean salir(){
        UIHelper.mostrar(AppLogger.getSessionSummary());
        return true;
    }
}