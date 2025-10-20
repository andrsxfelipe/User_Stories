import controller.TicketController;
import controller.UserController;
import domain.Category;
import domain.Ticket;
import domain.User;
import view.ViewHelper;

public static void main(String[] args) {
    ViewHelper view = new ViewHelper();
    boolean exit = false;

    UserController users = new UserController();
    TicketController tickets = new TicketController();

    while (!exit){
        String option = view.showMenu();
        switch (option) {
            case "1" -> {
                User newUser = view.createUser();
                if (newUser != null){
                    boolean created = users.createUser(newUser);
                    if (created){
                        System.out.println("Usuario creado exitosamente!");
                    } else {
                        System.out.println("Problema al crear el usuario.");
                    }
                }
            }
            case "2" -> {
                List<User> usersList = users.toList();
                view.showUsers(usersList);
            }
            case "3" -> {
                Ticket newTicket = view.createTicket();
                boolean ticketCreated = tickets.createTicket(newTicket);
                if (ticketCreated){
                    System.out.println("Ticket creado exitosamente");
                } else {
                    System.out.println("Problema al crear el ticket");
                }
            }
            case "4" -> {
                int[] assignation = view.assignTicket();
                boolean tickedAssigned = tickets.assignTicket(assignation);
                if (tickedAssigned){
                    System.out.println("Ticket asignado!");
                } else {
                    System.out.println("Error al asignar el ticket");
                }
            }
            case "5" -> {
                int ticketToUpdate = view.changeTicketStatus();
                boolean ticketUpdated = tickets.changeTicketStatus(ticketToUpdate);
                if (ticketUpdated){
                    System.out.println("Ticket actualizado!");
                } else {
                    System.out.println("Ticket no encontrado o ya se encuentra cerrado.");
                }
            }
            case "6" -> {
                int [] searchBy = view.searchByCatStat();
                List<Ticket> ticketsByCatStat = tickets.searchByCatStat(searchBy);
                view.showTickets(ticketsByCatStat);
            }
            case "7" -> {
                String asignee = view.searchByAssignee();
                List<Ticket> ticketsByAsignee = tickets.searchByAsignee(asignee);
                view.showTickets(ticketsByAsignee);
            }
            case "8" -> {
                List<Category> topThree = tickets.topThree();
                view.showCategory(topThree);
            }
            case "9" -> {
                exit = true;
            }
            default -> {

            }
        }

    }
}