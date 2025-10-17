public class Electrodomestico extends Producto{

    public Electrodomestico(String nombre, double precio, int stock){
        super(nombre, precio, stock);
    }

    @Override
    public String getDescription(){
        return String.format("""
                %s (Electrodomestico)
                 - Precio: %f | Cantidad disponible: %d
                """, getNombre(), getPrecio() , getStock());
    }

}
