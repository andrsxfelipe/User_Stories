package model;

public class Appliance extends Product{
    public Appliance(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public String getDescription(){
        return "Product: "+name+", Price: "+price+" COP, Stock: "+stock+", Kind of product: Appliance.";
    }
}