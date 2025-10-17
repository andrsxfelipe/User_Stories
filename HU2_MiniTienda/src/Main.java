import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        ArrayList<Producto> productos = new ArrayList<>();
        double[] precios = {};
        HashMap<String, Integer> stock = new HashMap<>();
        double totalCompras = 0;

        boolean exit = false;
        while (!exit){
            String[] opciones = {"Agregar Producto", "Listar inventario", "Comprar Producto",
                    "Estadísticas", "Buscar producto", "Salir"};
            int opcion = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?","Menu",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opciones,opciones[0]);
            switch (opcion){
                case 0 -> precios = agregarProducto(productos,precios,stock);
                case 1 -> listarProductos(productos,precios,stock);
                case 2 -> totalCompras = comprarProducto(stock,precios,totalCompras);
                case 3 -> estadisticas(productos);
                case 4 -> buscarPducto(productos);
                default -> {
                    JOptionPane.showMessageDialog(null,
                            "Gracias por usar el sistema!\nCompras totales: "+totalCompras);
                    exit=true;
                }
            }
        }
    }
    public static double[] agregarProducto(ArrayList<Producto> productos, double[] precios, HashMap<String, Integer> stock){
        try {
            String[] tipos = {"Electrodomestico", "Alimento"};
            int tipo = JOptionPane.showOptionDialog(
                    null, null,
                    "Ingrese el tipo de producto",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, tipos, tipos[0]);

            if (tipo == -1 ) return precios;

            JTextField nombreField = new JTextField();
            JTextField precioField = new JTextField();
            JTextField stockField = new JTextField();

            Object[] fields = {
                    "Producto: ", nombreField,
                    "Precio: ", precioField,
                    "Stock: ", stockField
            };

            int confirm = JOptionPane.showConfirmDialog(
                    null, fields,
                    "Ingrese los datos del producto", JOptionPane.OK_CANCEL_OPTION);

            if (confirm == JOptionPane.OK_OPTION) {

                String nombre = nombreField.getText();

                if (stock.containsKey(nombre)){
                    JOptionPane.showMessageDialog(null,
                            "Este producto ya se encuentra en la lista");
                } else {

                    String precioString = precioField.getText();
                    String cantidadString = stockField.getText();

                    if (nombre.isEmpty() || precioString.isEmpty() || cantidadString.isEmpty()){
                        throw new IllegalArgumentException("Debe llenar todos los campos.");
                    }

                    double precio = Double.parseDouble(precioString);
                    int cantidad = Integer.parseInt(cantidadString);

                    double[] nuevosPrecios = new double[precios.length + 1];
                    System.arraycopy(precios, 0, nuevosPrecios, 0, precios.length);
                    nuevosPrecios[precios.length] = precio;
                    precios = nuevosPrecios;
                    stock.put(nombre, cantidad);
                    switch (tipo) {
                        case 0 -> productos.add(new Electrodomestico(nombre, precio, cantidad));
                        case 1 -> productos.add(new Alimento(nombre, precio, cantidad));
                    }
                }
            }

        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "Los precios y cantidades deben ser números válidos mayores a cero",
                    "Entrada inválida",JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Entrada inválida",JOptionPane.ERROR_MESSAGE);
        }
        return precios;
    }

    public static void listarProductos(ArrayList<Producto> productos, double[] precios, HashMap<String, Integer> stock){
        StringBuilder pductos = new StringBuilder();
        ArrayList<Map.Entry<String,Integer>> entries = new ArrayList<>(stock.entrySet());
        for (int i=0 ; i < precios.length; i++){
            pductos.append(entries.get(i).getKey()).append(" | Cantidad: ").append(entries.get(i).getValue())
                    .append(" | Precio: ").append(precios[i]).append("\nDescripcion: ")
                    .append(productos.get(i).getDescription()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null,pductos);
    }

    public static double comprarProducto(HashMap<String,Integer> stock, double[] precios, double totalCompras) {
        try {
            Set<String> pductos = stock.keySet();
            Object[] opciones = pductos.toArray();
            if (opciones.length > 0) {
                int producto = JOptionPane.showOptionDialog(null, null,
                        "Comprar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        opciones, opciones[0]);
                if (producto == -1) return totalCompras;
                String cantStr = JOptionPane.showInputDialog("Ingrese la cantidad de producto que quiere comprar");
                int cant = Integer.parseInt(cantStr);
                if (cant<=0) throw new NumberFormatException();
                String productoAComprar = (String) opciones[producto];
                int stockActual = stock.get(productoAComprar);
                int nuevoStock = stockActual - cant;
                if (nuevoStock<0){
                    JOptionPane.showMessageDialog(null,"No hay cantidad suficiente");
                    return totalCompras;
                } else {
                    stock.put(productoAComprar,nuevoStock);
                    totalCompras += precios[producto]*cant;
                    JOptionPane.showMessageDialog(null,"Producto comprado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay productos");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "La cantidad debe ser un número entero positivo");
        }
        return totalCompras;
    }

    public static void estadisticas(List<Producto> pductos){
        if (pductos.isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay productos");
            return;
        }

        double minPrecio = pductos.getFirst().getPrecio();
        String minPducto="";
        double maxPrecio = pductos.getFirst().getPrecio();
        String maxPducto="";

        for (Producto p: pductos){
            if (minPrecio>p.getPrecio()){
                minPrecio = p.getPrecio();
                minPducto = p.getNombre();
            }
            if (maxPrecio < p.getPrecio()){
                maxPrecio = p.getPrecio();
                maxPducto = p.getNombre();
            }
        }

        String estadistics = String.format("""
                Producto con menor precio: %s | Precio: %f
                Producto con mayor precio: %s | Precio: %f
                """, minPducto,minPrecio,maxPducto,maxPrecio);
        JOptionPane.showMessageDialog(null,estadistics);
    }

    public static void buscarPducto(List<Producto> pductos){
        String pductoBuscado = JOptionPane.showInputDialog("Ingrese el producto que desea buscar").trim().toLowerCase();
        StringBuilder pductosEncontrados = new StringBuilder();
        for (Producto p : pductos){
            if (p.getNombre().trim().toLowerCase().contains(pductoBuscado)){
                pductosEncontrados.append(p.getDescription()).append("\n");
            }
        }
        if (!pductosEncontrados.isEmpty()){
            JOptionPane.showMessageDialog(null,pductosEncontrados);
        } else {
            JOptionPane.showMessageDialog(null,"No se encontraron productos");
        }
    }
}
