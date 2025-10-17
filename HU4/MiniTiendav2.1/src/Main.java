import service.ProductService;
import ui.UIHelper;

public class Main {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        boolean exit = false;
        String[] actions = {"Agregar Producto", "Listar Inventario", "Actualizar Precio/Stock",
                "Eliminar Producto", "Buscar por Nombre", "Buscar por ID","Salir"};
        while (!exit) {
            int action = UIHelper.menu("Bienvenido", actions);
            switch (action) {
                case 0 -> {
                    service.agregarProducto();
                }
                case 1 -> {
                    service.toList();
                }
                case 2 -> {
                    service.actualizar();
                }
                case 3 -> {
                    service.eliminarProducto();
                }
                case 4 -> {
                    service.buscarPorNombre();
                }
                case 5 -> {
                    service.searchById();
                }
                default -> {
                    UIHelper.show("Gracias por usar el servicio!");
                    exit = true;
                }
            }
        }
    }
}
