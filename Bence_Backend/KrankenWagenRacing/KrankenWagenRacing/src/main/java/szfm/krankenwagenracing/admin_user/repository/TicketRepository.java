package szfm.krankenwagenracing.admin_user.repository;

import szfm.krankenwagenracing.admin_user.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
