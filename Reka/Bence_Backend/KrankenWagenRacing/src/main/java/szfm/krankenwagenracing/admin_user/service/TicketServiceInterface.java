package szfm.krankenwagenracing.admin_user.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.model.Ticket;

public interface TicketServiceInterface {
    Ticket save(TicketDTo ticketDTo);
}

