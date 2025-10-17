import service.ServicioInventario;
import utils.UIHelper;

import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final ServicioInventario inventario = new ServicioInventario();

    public static void main(String[] args) {
        int eleccion = UIHelper.elegirOpcion(
                "¿Qué desea hacer?","Agregar producto","Listar inventario","Actualizar precio",
                "Actualizar stock","ELiminar producto","Buscar producto por nombre","Salir"
        );
        switch (eleccion){
            case 0 -> agregarProducto();
            case 1 -> actualizarPrecio();
            //case 2 ->
            //case 3 ->
            //case 4 ->
            //case 5 ->
        }
    }

    public static void agregarProducto(){
        Map<String,String> infoLibro = UIHelper.mostrarForm("Agregar","Nombre","Precio","Cantidad");
        String nombre = infoLibro.get("Nombre");
        String precio = infoLibro.get("Precio");
        String cantidad = infoLibro.get("Cantidad");

        inventario.agregarProducto(nombre, precio, cantidad);
    }

    public static void actualizarPrecio(){
        Map<String, String> precioInfo =
                UIHelper.mostrarForm("Actualizar precio","ID Producto","Nuevo Precio");
        String id = precioInfo.get("ID Producto");
        String nuevoPrecio = precioInfo.get("Nuevo Precio");

        inventario.actualizarPrecio(id, nuevoPrecio);
    }

    public void actualizarStock() {
        Map<String, String> stockInfo =
                UIHelper.mostrarForm("Actualizar stock","ID Producto","Nuevo Stock");
        String id = stockInfo.get("ID Producto");
        String nuevoStock = stockInfo.get("Nuevo Stock");

        inventario.actualizarStock(id, nuevoStock);
    }

    public void eliminarProducto() {
        inventario.eliminarProducto(UIHelper.ingresar("Ingrese el ID del producto a eliminar"));
    }

    public void buscarPorNombre() {
        inventario.buscarPorNombre(UIHelper.ingresar("¿Qué producto busca?"));
    }
}