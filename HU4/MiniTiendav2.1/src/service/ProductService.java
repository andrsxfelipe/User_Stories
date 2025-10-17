package service;

import model.Producto;
import repository.ProductDAO;
import ui.UIHelper;
import util.InputHelper;

import javax.swing.*;
import java.util.List;

public class ProductService implements IProductService{
    private static final ProductDAO dao = new ProductDAO();
    @Override
    public void agregarProducto(){
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        Object[] newProductFields = {
                "Producto:",nameField,
                "Precio:",priceField,
                "Stock:",stockField
        };

        int confirmAddProduct = UIHelper.confirm("Agregar Producto",newProductFields);

        if (confirmAddProduct == JOptionPane.OK_OPTION){
            try {
                String productName = InputHelper.validateStr(nameField.getText());
                Double productPrice = InputHelper.validateDouble(priceField.getText());
                int productStock = InputHelper.validateInt(priceField.getText());

                Producto p = new Producto(productName,productPrice,productStock);

                dao.crear(p);

            } catch (IllegalArgumentException e){
                UIHelper.showError(e.getMessage(),"Entrada inválida.");
            }
        }
    }

    @Override
    public void actualizar(){
        JTextField idProductToUpdate = new JTextField();
        JTextField newPriceField = new JTextField();
        JTextField newStockField = new JTextField();

        Object[] uptePductFields = {
                "ID del producto:", idProductToUpdate,
                "Nuevo precio:", newPriceField,
                "Nuevo Stock:", newStockField
        };

        int confirmUpdateProduct = UIHelper.confirm("Actualizar producto",uptePductFields);
        if (confirmUpdateProduct == JOptionPane.OK_OPTION){
            try {
                int productID = InputHelper.validateInt(idProductToUpdate.getText());
                Double newPrice = InputHelper.validateDouble(newPriceField.getText());
                int newStock = InputHelper.validateInt(newStockField.getText());

                Producto pductUpdated = new Producto(productID,"0",newPrice,newStock);

                dao.actualizar(pductUpdated);
            } catch (IllegalArgumentException e){
                UIHelper.showError(e.getMessage(),"Error de entrada");
            }
        }
    }

    @Override
    public void eliminarProducto() {
        try {
            String idProductStr = UIHelper.input("Ingrese el ID del producto a eliminar: ");
            int idProducto = InputHelper.validateInt(idProductStr);
            dao.eliminar(idProducto);
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada Inválida");
        }
    }

    @Override
    public void buscarPorNombre() {
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

    @Override
    public void searchById(){
        try {
            String pductToSearchStr = UIHelper.input("Ingrese el id del producto que quiere buscar:");
            int pductToSearch = InputHelper.validateInt(pductToSearchStr);
            Producto serchedPduct = dao.buscarPorId(pductToSearch);
            UIHelper.show(serchedPduct.getInfo());
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada Inválida");
        }
    }

    public void toList(){
        String productList = "";
        for (Producto p: dao.listar()){
            productList += p.getInfo();
        }
        UIHelper.show(productList);
    }
}
