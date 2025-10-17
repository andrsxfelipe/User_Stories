package service;

import dao.ProductoDAO;
import model.Producto;
import ui.UIHelper;
import util.InputHelper;

import javax.swing.*;
import java.util.List;

public class ProductService {
    private static final ProductoDAO dao = new ProductoDAO();
    public static void addProduct(){
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();

        Object[] newProductFields = {
                "Producto:",nameField,
                "Precio:",priceField,
                "Cantidad:",stockField
        };
        int confirmAddProduct = UIHelper.confirm("Agregar Producto",newProductFields);
        if (confirmAddProduct == JOptionPane.OK_OPTION){
            try {
                String productName = InputHelper.validateStr(nameField.getText());
                String productPriceStr = priceField.getText();
                String productStockStr = stockField.getText();

                double productPrice = InputHelper.validateDouble(productPriceStr);
                int productStock = InputHelper.validateInt(productStockStr);

                Producto product = new Producto(productName, productPrice, productStock);
                dao.insertar(product);
            } catch (IllegalArgumentException e){
                UIHelper.showError(e.getMessage(),"Entrada Inválida.");
            }
        }
    }

    public static void toList(){
        String productList = "";
        for (Producto p: dao.listar()){
            productList += p.getInfo();
        }
        UIHelper.show(productList);
    }

    public static void updatePrice(){
        JTextField idField = new JTextField();
        JTextField priceField = new JTextField();
        Object[] newPriceFields = {
                "ID del producto:",idField,
                "Precio:",priceField,
        };
        int confirmUpdatePrice = UIHelper.confirm("Actualizar precio", newPriceFields);
        if (confirmUpdatePrice == JOptionPane.OK_OPTION){
            try {
                String idProductStr = idField.getText();
                String newPriceStr = priceField.getText();

                int idProduct = InputHelper.validateInt(idProductStr);
                double newPrice = InputHelper.validateDouble(newPriceStr);
                dao.actualizarPrecio(idProduct, newPrice);
            } catch (IllegalArgumentException e){
                UIHelper.showError(e.getMessage(),"Entrada Inválida");
            }
        }
    }

    public static void updateStock(){
        JTextField idField = new JTextField();
        JTextField stockField = new JTextField();
        Object[] newStockFields = {
                "ID del producto: ", idField,
                "Nuevo Stock: ", stockField
        };
        int confirmUpdateStock = UIHelper.confirm("Actualizar stock", newStockFields);
        if (confirmUpdateStock == JOptionPane.OK_OPTION){
            try {
                String idProductStr = idField.getText();
                String newStockStr = stockField.getText();

                int idProduct = InputHelper.validateInt(idProductStr);
                int newStock = InputHelper.validateInt(newStockStr);
                dao.actualizarStock(idProduct, newStock);
            }
            catch (IllegalArgumentException e){
                UIHelper.showError(e.getMessage(),"Entrada inválida.");
            }
        }
    }

    public static void delete(){
        try {
            String idProductStr = UIHelper.input("Ingrese el ID del producto a eliminar: ");
            int idProducto = InputHelper.validateInt(idProductStr);
            dao.eliminar(idProducto);
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada Inválida");
        }
    }

    public static void searchProduct(){
        try {
            String productName = UIHelper.input("Ingrese el nombre del producto que desea buscar: ");
            List<Producto> produdctsFound = dao.buscarProducto(productName);
            if (!produdctsFound.isEmpty()) {
                String produdctsFoundList = "";
                for (Producto p : produdctsFound) {
                    produdctsFoundList += p.getInfo();
                }
                UIHelper.show(produdctsFoundList);
            } else {
                UIHelper.show("No se encontraron productos con el nombre " + productName + ".");
            }
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada Inválida");
        }
    }
}
