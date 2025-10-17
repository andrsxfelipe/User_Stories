import model.Appliance;
import model.Food;
import model.Product;
import service.ProductService;

import javax.swing.*;

public class Menu {
    private final ProductService service;
    boolean exit = false;

    public Menu() {
        this.service = new ProductService();
    }

    public void start(){
        JOptionPane.showMessageDialog(null, "Welcome to the Mini Store System Information Manager.");
        while (!exit){
            String[] actions = {"Add product", "Show inventory", "Buy product", "Stadistics", "Search for a product","Exit"};
            String[] productKinds = {"Appliance","Food"};
            int option = JOptionPane.showOptionDialog(
                    null,
                    "What would you like to do: ",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    actions,
                    actions[0]
            );
            switch (option){
                // Add a product.
                case 0:
                    String productName = JOptionPane.showInputDialog("Product Name: ");

                    String priceStr = JOptionPane.showInputDialog("Price: ");
                    double price = Double.parseDouble(priceStr);

                    String stockStr = JOptionPane.showInputDialog("Amount of the product: ");
                    int stock = Integer.parseInt(stockStr);

                    int kindOfProduct = JOptionPane.showOptionDialog(
                            null,
                            "What kind of product are you going to add?",
                            "Kind of product",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            productKinds,
                            productKinds[0]
                    );
                    switch (kindOfProduct){
                        case 0:
                            Product p = new Appliance(productName,price,stock);
                            service.create(p);
                            break;
                        case 1:
                            Product p1 = new Food(productName,price, stock);
                            service.create(p1);
                            break;
                    }
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, service.readProducts());
                    break;
                case 2:
                    int productToBuy = JOptionPane.showOptionDialog(
                            null,
                            "What product do you wanna buy?",
                            "Buy a product",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            service.listProducts(),
                            service.listProducts()[0]
                    );
                    String amountToBuyStr = JOptionPane.showInputDialog(null, "What is the amount of product would you like to buy?");
                    int amountToBuy = Integer.parseInt(amountToBuyStr);
                    String message = service.buyProduct(productToBuy, amountToBuy);
                    JOptionPane.showMessageDialog(null, message);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, service.getMaxandMinPducts());
                    break;
                case 4:
                    String pductToSearch = JOptionPane.showInputDialog(null,"Type the product(s) you would like to search: ");
                    String pductsFound = service.searchForPducts(pductToSearch);
                    JOptionPane.showMessageDialog(null, "Products found:\n"+pductsFound);
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null,"Thanks for use the system! Goodbye.");
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Invalid option, try again.");
                    break;
            }
        }
    }
}
