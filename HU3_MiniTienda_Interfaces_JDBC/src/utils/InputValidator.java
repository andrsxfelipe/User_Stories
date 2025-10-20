package utils;

public class InputValidator {
    public static String capitalizar(String texto){
        if (texto == null || texto.isEmpty()){
            return texto;
        } else {
            return texto.substring(0,1).toUpperCase()+texto.substring(1).toLowerCase();
        }
    }
}
