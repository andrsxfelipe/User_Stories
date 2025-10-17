package util;

public class InputHelper {
    public static String validateStr(String input){
        if (input == null || input.trim().isEmpty()){
            throw new IllegalArgumentException("Por favor, llena todos los campos.");
        }
        return input.trim();
    }

    public static double validateDouble(String input){
        try {
            double doubleInput = Double.parseDouble(input.trim());
            if (doubleInput < 0){
                throw new IllegalArgumentException("El valor numérico no puede ser negativo.");
            }
            return doubleInput;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El valor numérico debe ser un número válido.");
        }
    }

    public static int validateInt(String input){
        try {
            int intInput = Integer.parseInt(input);
            if (intInput<0){
                throw new IllegalArgumentException("El valor numérico no debe ser negativo");
            }
            return intInput;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El valor numérico deber ser un número válido.");
        }
    }
}
