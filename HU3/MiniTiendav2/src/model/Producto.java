package model;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String nombre, double precio, int stock){
        this(0, nombre, precio, stock);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getInfo(){
        return getId() + " Producto: "+getNombre()+" - Precio: "+getPrecio()+" - Stock: "+getStock()+"\n";
    }
}
