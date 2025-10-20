package util;

public class InputHelper {
    public static String validateRole(String role){
        if (role.equalsIgnoreCase("reporter") || role.equalsIgnoreCase("assignee")){
            return role.toLowerCase();
        } else {
            throw new IllegalArgumentException("El rol debe ser reporter o assignee");
        }
    }
}
