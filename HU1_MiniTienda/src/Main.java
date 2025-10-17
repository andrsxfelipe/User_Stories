import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> productos = new ArrayList<>();
        double[] precios = new double[0];
        HashMap<String, Integer> stocks = new HashMap<>();

        double totalCompras = 0;

        String[] opciones = {"Agregar Producto", "Listar inventario", "Comprar producto",
        "Mostrar estadísticas", "Buscar producto por nombre", "Salir"};

        boolean exit = false;
        while (!exit){
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué desea hacer?",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            switch (opcion){
                case 0 -> {
                    JTextField productoField = new JTextField();
                    JTextField precioField = new JTextField();
                    JTextField stockField = new JTextField();
                    Object [] fields = {
                            "Producto: ",
                    };
                    int confirm = JOptionPane.showConfirmDialog(null, fields, "Ingrese los datos",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (confirm == JOptionPane.OK_OPTION){
                        try {
                            String producto = productoField.getText();
                            double precio = Double.parseDouble(precioField.getText());
                            int stock = Integer.parseInt(stockField.getText());

                            if (producto.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "El nombre del producto no puede estar vacío");
                                break;
                            }
                            if (productos.contains(producto)) {
                                JOptionPane.showMessageDialog(null, "El producto ya existe");
                            } else {
                                double[] nuevosPrecios = new double[precios.length + 1];
                                System.arraycopy(precios, 0, nuevosPrecios, 0, precios.length);
                                nuevosPrecios[precios.length] = precio;
                                precios = nuevosPrecios;

                                productos.add(producto);
                                stocks.put(producto, stock);

                                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                            }
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null,
                                    "Los valores deben ser datos válidos");
                        }
                    }
                }
                case 1 -> {
                    if (productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El inventario está vacío");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < productos.size(); i++) {
                            String nombre = productos.get(i);
                            double precio = precios[i];
                            int cantidad = stocks.getOrDefault(nombre, 0);

                            sb.append("Producto: ").append(nombre)
                                    .append(" | Precio: $").append(precio)
                                    .append(" | Stock: ").append(cantidad)
                                    .append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString(), "Inventario",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 2 -> {
                    if (productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El inventario está vacío");
                    } else {
                        JTextField productoField = new JTextField();
                        JTextField cantidadField = new JTextField();
                        Object[] fields = {
                                "Producto:", productoField,
                                "Cantidad a comprar:", cantidadField
                        };

                        int confirm = JOptionPane.showConfirmDialog(null, fields,
                                "Compra de producto", JOptionPane.OK_CANCEL_OPTION);

                        if (confirm == JOptionPane.OK_OPTION) {
                            String nombre = productoField.getText().trim();
                            try {
                                int cantidad = Integer.parseInt(cantidadField.getText().trim());

                                if (!productos.contains(nombre)) {
                                    JOptionPane.showMessageDialog(null, "El producto no existe en el inventario");
                                } else {
                                    int index = productos.indexOf(nombre);
                                    double precioUnit = precios[index];
                                    int stockActual = stocks.getOrDefault(nombre, 0);

                                    if (cantidad <= 0) {
                                        JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida");
                                    } else if (cantidad > stockActual) {
                                        JOptionPane.showMessageDialog(null,
                                                "No hay suficiente stock. Stock disponible: " + stockActual);
                                    } else {
                                        int nuevoStock = stockActual - cantidad;
                                        stocks.put(nombre, nuevoStock);
                                        double totalCompra = cantidad * precioUnit;
                                        totalCompras += totalCompra;
                                        JOptionPane.showMessageDialog(null,
                                                "Compra realizada correctamente.\nTotal: $" + totalCompra +
                                                        "\nStock restante: " + nuevoStock);
                                    }
                                }

                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null,
                                        "La cantidad debe ser un número entero válido");
                            }
                        }
                    }
                }

                case 3 -> {
                    if (productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El inventario está vacío");
                    } else {
                        double precioMin = precios[0];
                        double precioMax = precios[0];
                        String productoMin = productos.get(0);
                        String productoMax = productos.get(0);

                        for (int i = 1; i < precios.length; i++) {
                            if (precios[i] < precioMin) {
                                precioMin = precios[i];
                                productoMin = productos.get(i);
                            }
                            if (precios[i] > precioMax) {
                                precioMax = precios[i];
                                productoMax = productos.get(i);
                            }
                        }

                        String mensaje = String.format(
                                "Producto más barato: %s ($%.2f)\nProducto más caro: %s ($%.2f)",
                                productoMin, precioMin, productoMax, precioMax
                        );

                        JOptionPane.showMessageDialog(null, mensaje,
                                "Estadísticas de precios", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 4 -> {
                    if (productos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El inventario está vacío");
                    } else {
                        String busqueda = JOptionPane.showInputDialog(null,
                                "Ingrese el nombre o parte del nombre del producto a buscar",
                                "Buscar producto",
                                JOptionPane.QUESTION_MESSAGE);

                        if (busqueda != null && !busqueda.trim().isEmpty()) {
                            busqueda = busqueda.trim().toLowerCase();
                            StringBuilder resultados = new StringBuilder();

                            for (int i = 0; i < productos.size(); i++) {
                                String nombre = productos.get(i);
                                if (nombre.toLowerCase().contains(busqueda)) {
                                    double precio = precios[i];
                                    int cantidad = stocks.getOrDefault(nombre, 0);
                                    resultados.append("Producto: ").append(nombre)
                                            .append(" | Precio: $").append(precio)
                                            .append(" | Stock: ").append(cantidad)
                                            .append("\n");
                                }
                            }

                            if (resultados.length() == 0) {
                                JOptionPane.showMessageDialog(null, "No se encontraron productos que coincidan con la búsqueda");
                            } else {
                                JOptionPane.showMessageDialog(null, resultados.toString(), "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe ingresar un término de búsqueda válido");
                        }
                    }
                }
                default -> {
                    JOptionPane.showMessageDialog(null,
                            "Gracias por usar el sistema.\nTotal acumulado de compras: $" + totalCompras);
                    exit = true;
                }
            }
        }
    }
}