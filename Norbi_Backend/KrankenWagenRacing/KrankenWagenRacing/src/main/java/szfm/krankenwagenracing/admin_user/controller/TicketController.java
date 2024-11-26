package szfm.krankenwagenracing.admin_user.controller;

import szfm.krankenwagenracing.admin_user.model.Ticket;
import szfm.krankenwagenracing.admin_user.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/tickets/{id}")
    public String getTicketById(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket-details";
    }
}
