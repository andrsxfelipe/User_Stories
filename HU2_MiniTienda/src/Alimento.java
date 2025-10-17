public class Alimento extends Producto {

    public Alimento(String nombre, double precio, int stock){
        super(nombre,precio,stock);
    }

    @Override
    public String getDescription(){
        return String.format("""
                %s (Alimento)
                - Precio: %f | Cantidad disponible: %d
                """, getNombre(), getPrecio(), getStock());
    }
}
