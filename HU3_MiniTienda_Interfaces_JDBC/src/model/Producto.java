package model;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int id, double precio) {
        this.id = id;
        this.precio = precio;
    }
}
