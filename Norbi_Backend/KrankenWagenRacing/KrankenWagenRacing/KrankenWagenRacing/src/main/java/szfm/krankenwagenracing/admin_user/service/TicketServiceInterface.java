package szfm.krankenwagenracing.admin_user.service;

import org.springframework.context.annotation.Bean;
import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.model.Ticket;
public interface TicketServiceInterface {
    @Bean
    Ticket save(TicketDTo ticketDTo);
}

