public abstract class Producto {
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock){

        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;

    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public abstract String getDescription();
}
