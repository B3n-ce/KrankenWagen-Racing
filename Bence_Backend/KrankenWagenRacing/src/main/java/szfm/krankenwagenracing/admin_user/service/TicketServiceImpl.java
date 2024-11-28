package szfm.krankenwagenracing.admin_user.service;

import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.model.Ticket;
import szfm.krankenwagenracing.admin_user.repository.TicketRepository;

public class TicketServiceImpl implements TicketServiceInterface{

    private TicketRepository ticketRepository;


    @Override
    public Ticket save(TicketDTo ticketDTo) {
        Ticket ticket = new Ticket(ticketDTo.getName(), ticketDTo.getPrice(), ticketDTo.getEmail(), ticketDTo.getDate());
        return ticketRepository.save(ticket);
    }
}
