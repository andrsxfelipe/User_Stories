package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public void create(Product product){
        products.add(product);
    }

    public String readProducts(){
        String productListed = "";
        for (Product p : products){
            productListed += " -"+p.getDescription()+"\n";
        }
        return productListed;
    }

    public String[] listProducts(){
        List<String> productsName = new ArrayList<>();
        for (Product p: products){
            productsName.add(p.getName());
        }
        return productsName.toArray(new String[0]);
    }
    public String buyProduct(int p, int amount){
        if (products.get(p).getStock() < amount){
            return "Sorry, there's no enough amount of this product to buy";
        } else {
            products.get(p).setStock(amount);
            return "Product bought!";
        }
    }
    public String getMaxandMinPducts(){
        int minPriceIndex = 0;
        int maxPriceIndex = 0;
        for (int p=0; p<products.size(); p++){
            double minPrice = products.get(minPriceIndex).getPrice();
            double maxPrice = products.get(maxPriceIndex).getPrice();
            double currentPrice = products.get(p).getPrice();
            if (currentPrice > maxPrice){
                maxPriceIndex = p;
            }
            if (minPrice > currentPrice){
                minPriceIndex = p;
            }
        }
        return "Lowest price product:\n -"+products.get(minPriceIndex).getDescription()+"\nHighest price product:\n -"+
                products.get(maxPriceIndex).getDescription();
    }

    public String searchForPducts(String pduct){
        String pductsFound = "";
        for (Product p : products){
            if (p.getName().toLowerCase().contains(pduct.toLowerCase())){
                pductsFound += p.getDescription()+"\n";
            }
        }
        if (pductsFound.isEmpty()){
            return "This product was not found";
        }
        return pductsFound;
    }
}
