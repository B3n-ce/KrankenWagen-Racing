package szfm.krankenwagenracing.admin_user.service;

import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.model.Ticket;

import java.util.List;

public interface TicketServiceInterface {
    Ticket save(TicketDTo ticketDTo);

    List<Ticket> findTicketsByEmail(String email);

    void deleteTicketById(Long ticketId);
}