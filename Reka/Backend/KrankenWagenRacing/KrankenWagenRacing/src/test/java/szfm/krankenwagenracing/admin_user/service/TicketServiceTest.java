package szfm.krankenwagenracing.admin_user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.model.Ticket;
import szfm.krankenwagenracing.admin_user.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private TicketDTo ticketDTo;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ticketDTo = new TicketDTo("Ticket Name", 100, "testuser@example.com", "2024-12-01", "VIP");
        ticket = new Ticket("Ticket Name", 100, "testuser@example.com", "2024-12-01", "VIP");
    }

    @Test
    void testFindTicketsByEmail() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        when(ticketRepository.findByEmail(anyString())).thenReturn(tickets);

        List<Ticket> result = ticketService.findTicketsByEmail("testuser@example.com");

        assertNotNull(result, "A jegy lista nem lehet null");
        assertEquals(1, result.size(), "A lista mérete nem megfelelő");
        assertEquals("Ticket Name", result.get(0).getName(), "A jegy neve nem megfelelő");

        verify(ticketRepository, times(1)).findByEmail("testuser@example.com");
    }

    @Test
    void testSaveTicket() {
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket savedTicket = ticketService.save(ticketDTo);

        assertNotNull(savedTicket, "A mentett jegy nem lehet null");
        assertEquals("Ticket Name", savedTicket.getName(), "A mentett jegy neve nem megfelelő");
        assertEquals(100, savedTicket.getPrice(), "A mentett jegy ára nem megfelelő");

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testDeleteTicketById() {
        Long ticketId = 1L;

        doNothing().when(ticketRepository).deleteById(ticketId);

        ticketService.deleteTicketById(ticketId);

        verify(ticketRepository, times(1)).deleteById(ticketId);
    }
}