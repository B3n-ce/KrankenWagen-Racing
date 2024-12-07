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
    private TicketRepository ticketRepository; // Mockoljuk a TicketRepository-t

    @InjectMocks
    private TicketServiceImpl ticketService; // A szolgáltatás, ami használja a repository-t

    private Ticket ticket1;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {
        // Inicializáljuk a mockokat
        MockitoAnnotations.openMocks(this);

        // Létrehozunk néhány teszt jegyet
        ticket1 = new Ticket("VIP", 10000, "test@example.com", "2024-12-07", "Race");
        ticket2 = new Ticket("Regular", 5000, "test@example.com", "2024-12-08", "Qualifying");
    }

    @Test
    void testFindByEmail() {
        // Mockoljuk a findByEmail metódust
        when(ticketRepository.findByEmail("test@example.com")).thenReturn(Arrays.asList(ticket1, ticket2));

        // Meghívjuk a repository findByEmail metódusát
        List<Ticket> foundTickets = ticketRepository.findByEmail("test@example.com");

        // Ellenőrizzük, hogy két jegyet kaptunk
        assertNotNull(foundTickets, "A visszaadott lista nem lehet null.");
        assertEquals(2, foundTickets.size(), "Két jegyet kellett volna visszaadni.");

        // Ellenőrizzük, hogy az első jegy típusa 'VIP'
        assertEquals("Race", foundTickets.get(0).getType(), "Az első jegy típusa nem megfelelő.");

        // Ellenőrizzük, hogy a második jegy típusa 'Regular'
        assertEquals("Qualifying", foundTickets.get(1).getType(), "A második jegy típusa nem megfelelő.");
    }

    @Test
    void testFindByEmail_NotFound() {
        // Mockoljuk, hogy nem találunk semmilyen jegyet
        when(ticketRepository.findByEmail("nonexistent@example.com")).thenReturn(Arrays.asList());

        // Meghívjuk a repository findByEmail metódusát
        List<Ticket> foundTickets = ticketRepository.findByEmail("nonexistent@example.com");

        // Ellenőrizzük, hogy a visszaadott lista üres
        assertNotNull(foundTickets, "A visszaadott lista nem lehet null.");
        assertTrue(foundTickets.isEmpty(), "Nem kellene jegyet találni.");
    }
}