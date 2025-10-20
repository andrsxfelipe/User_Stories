package view;

import domain.Category;
import domain.Role;
import domain.Ticket;
import domain.User;
import util.InputHelper;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ViewHelper {
    Scanner sc = new Scanner(System.in);
    private static final Map<String, Integer> ROLES = Map.of(
            "reporter", 1,
            "assignee", 2
    );
    public String showMenu() {
        System.out.println("1. Registrar usuario");
        System.out.println("2. Listar usuario");
        System.out.println("3. Crear ticket");
        System.out.println("4. Asignar ticket");
        System.out.println("5. Cambiar estado de ticket");
        System.out.println("6. Buscar ticket por categoría");
        System.out.println("7. Listar tickets por assignee");
        System.out.println("8. Top 3 categorías con más tickets");
        System.out.println("9. Salir");
        return sc.nextLine();
    }

    public User createUser(){
        try {
            System.out.println("Ingrese el nombre del nuevo usuario:");
            String newUserName = sc.nextLine();

            System.out.println("Ingrese el rol del usuario (reporter/assignee)");
            String newUserRole = InputHelper.validateRole(sc.nextLine());

            return new User(newUserName, new Role(ROLES.get(newUserRole),newUserRole));
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void showUsers(List<User> users){
        System.out.println("Usuarios: ");
        for (User u : users){
            System.out.println(u.getInfo());
        }
    }

    public void showTickets(List<Ticket> tickets){
        System.out.println("Tickets: ");
        for (Ticket t : tickets){
            System.out.println(t.getInfo());
        }
    }

    public void showCategory(List<Category> categories){
        System.out.println("Categorias: ");
        for (Category c : categories){
            System.out.println(c.getInfo());
        }
    }

    public Ticket createTicket(){
        System.out.println("Ingrese el título del ticket: ");
        String ticketTitle = sc.nextLine();

        System.out.println("Ingrese la descripción del ticket: ");
        String ticketDescription = sc.nextLine();

        System.out.println("Ingrese el ID del asignador (reporter): ");
        int reporterId = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la categoría del ticket: 1. Infraestructura, 2. Aplicación, 3. Cuenta: ");
        int categoryId = sc.nextInt();
        sc.nextLine();

        return new Ticket(ticketTitle,ticketDescription, new User(reporterId),new Category(categoryId));
    }

    public int[] assignTicket(){
        System.out.println("Ingrese el ID del ticket:");
        int idTicket = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el ID de la persona asignada:");
        int idAssignee = sc.nextInt();
        sc.nextLine();

        int[] assignation = {idTicket,idAssignee};

        return assignation;
    }

    public int changeTicketStatus(){
        System.out.println("Ingrese el ID del ticket al que le quiere cambiar el estado.");
        int idTicket = sc.nextInt();
        sc.nextLine();
        return idTicket;
    }

    public int[] searchByCatStat(){
        int [] searchBy = new int[2];
        System.out.println("Ingrese la categoria por la que desea buscar 1. Infrastructura, 2. Aplicacion, 3. Cuenta:");
        int category = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el estado por el que desea buscar 1. Abierto, 2. En proceso, 3. Cerrado:");
        int status = sc.nextInt();
        sc.nextLine();

        searchBy[0] = category;
        searchBy[1] = status;
        return searchBy;
    }

    public String searchByAssignee(){
        System.out.println("Ingrese el nombre del asignado: ");
        return sc.nextLine();
    }
}
