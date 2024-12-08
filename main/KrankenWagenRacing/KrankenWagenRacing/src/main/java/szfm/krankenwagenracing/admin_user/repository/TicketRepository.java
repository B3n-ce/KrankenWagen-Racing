package szfm.krankenwagenracing.admin_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import szfm.krankenwagenracing.admin_user.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEmail(String email);
}