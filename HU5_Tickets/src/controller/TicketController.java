package controller;

import domain.Category;
import domain.Ticket;
import service.TicketService;

import java.util.List;

public class TicketController {
    private TicketService ticketService = new TicketService();
    public boolean createTicket (Ticket t){
        return ticketService.createTicket(t);
    }

    public boolean assignTicket (int[] assignation){
        return ticketService.assignTicket(assignation);
    }

    public boolean changeTicketStatus(int t){
        return ticketService.changeTicketStatus(t);
    }

    public List<Ticket> searchByCatStat(int[] s){
        return ticketService.searchByCatStat(s);
    }

    public List<Ticket> searchByAsignee(String a){
        return ticketService.searchByAsignee(a);
    }

    public List<Category> topThree(){
        return ticketService.topThree();
    }
}
