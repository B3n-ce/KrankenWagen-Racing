package szfm.krankenwagenracing.admin_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szfm.krankenwagenracing.admin_user.model.Ticket;
import szfm.krankenwagenracing.admin_user.repository.TicketRepository;
import szfm.krankenwagenracing.admin_user.dto.TicketDTo;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketServiceInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findTicketsByEmail(String email) {
        return ticketRepository.findByEmail(email);
    }

    @Override
    public Ticket save(TicketDTo ticketDTo) {
        Ticket ticket = new Ticket(ticketDTo.getName(), ticketDTo.getPrice(), ticketDTo.getEmail(), ticketDTo.getDate(), ticketDTo.getType());
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}