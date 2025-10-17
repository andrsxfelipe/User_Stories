package model;

abstract public class Product {
    String name;
    double price;
    int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public  abstract String getDescription();

    public int getStock() {
        return stock;
    }

    public void setStock(int amount) {
        this.stock -= amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}