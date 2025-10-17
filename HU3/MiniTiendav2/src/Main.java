import ui.UIHelper;
import service.ProductService;
public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        String[] actions = {"Agregar Producto", "Listar Inventario", "Actualizar Precio", "Actualizar Stock",
                "Eliminar Producto", "Buscar por Nombre", "Salir"};
        while (!exit) {
            int action = UIHelper.menu("Bienvenido", actions);
            switch (action) {
                case 0 -> {
                    ProductService.addProduct();
                }
                case 1 -> {
                    ProductService.toList();
                }
                case 2 -> {
                    ProductService.updatePrice();
                }
                case 3 -> {
                    ProductService.updateStock();
                }
                case 4 -> {
                    ProductService.delete();
                }
                case 5 -> {
                    ProductService.searchProduct();
                }
                default -> {
                    UIHelper.show("Gracias por usar el servicio!");
                    exit = true;
                }
            }
        }
    }

}
