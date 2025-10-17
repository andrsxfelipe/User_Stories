package model;

public class Food extends Product{
    public Food(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public String getDescription(){
        return "Product: "+name+", Price: "+price+" COP, Stock: "+stock+", Kind of product: Food.";
    }
}
