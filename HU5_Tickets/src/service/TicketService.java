package service;

import dao.TicketDAO;
import dao.UserDAO;
import domain.Category;
import domain.Ticket;

import java.util.List;

public class TicketService {
    private final UserDAO userDAO = new UserDAO();
    private final TicketDAO ticketDAO = new TicketDAO();

    public boolean createTicket(Ticket t){
        return ticketDAO.createTicket(t);
    }

    public boolean assignTicket(int[] assignation){
        return ticketDAO.assignTicket(assignation);
    }

    public boolean changeTicketStatus(int t){
        return ticketDAO.changeTicketStatus(t);
    }

    public List<Ticket> searchByCatStat(int[] s){
        return ticketDAO.searchByCatStat(s);
    }

    public List<Ticket> searchByAsignee(String a){
        return ticketDAO.searchByAsignee(a);
    }
    public List<Category> topThree(){
        return ticketDAO.topThree();
    }
}
