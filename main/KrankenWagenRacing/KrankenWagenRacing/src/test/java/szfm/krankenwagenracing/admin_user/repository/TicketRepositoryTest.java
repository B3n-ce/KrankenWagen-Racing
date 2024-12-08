package szfm.krankenwagenracing.admin_user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import szfm.krankenwagenracing.admin_user.model.Ticket;
import szfm.krankenwagenracing.admin_user.service.TicketServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketRepositoryTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket1;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ticket1 = new Ticket("VIP", 10000, "test@example.com", "2024-12-07", "Race");
        ticket2 = new Ticket("Regular", 5000, "test@example.com", "2024-12-08", "Qualifying");
    }

    @Test
    void testFindByEmail() {
        when(ticketRepository.findByEmail("test@example.com")).thenReturn(Arrays.asList(ticket1, ticket2));

        List<Ticket> foundTickets = ticketRepository.findByEmail("test@example.com");

        assertNotNull(foundTickets, "A visszaadott lista nem lehet null.");
        assertEquals(2, foundTickets.size(), "Két jegyet kellett volna visszaadni.");

        assertEquals("Race", foundTickets.get(0).getType(), "Az első jegy típusa nem megfelelő.");

        assertEquals("Qualifying", foundTickets.get(1).getType(), "A második jegy típusa nem megfelelő.");
    }

    @Test
    void testFindByEmail_NotFound() {
        when(ticketRepository.findByEmail("nonexistent@example.com")).thenReturn(Arrays.asList());

        List<Ticket> foundTickets = ticketRepository.findByEmail("nonexistent@example.com");

        assertNotNull(foundTickets, "A visszaadott lista nem lehet null.");
        assertTrue(foundTickets.isEmpty(), "Nem kellene jegyet találni.");
    }
}