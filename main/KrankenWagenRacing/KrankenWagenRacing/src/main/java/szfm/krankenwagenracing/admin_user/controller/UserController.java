package szfm.krankenwagenracing.admin_user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.dto.UserDto;
import szfm.krankenwagenracing.admin_user.service.TicketServiceInterface;
import szfm.krankenwagenracing.admin_user.model.Ticket;
import szfm.krankenwagenracing.admin_user.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TicketServiceInterface ticketServiceInterface;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getMainPage()
    {
        return "main";
    }

    @GetMapping("/registration")
    public String getRegisterPage(@ModelAttribute("user") UserDto userDto)
    {
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        if (userService.emailExists(userDto.getEmail())) {
            model.addAttribute("errorMessage", "Ezzel az e-mail címmel már regisztráltak!");
            return "register";
        }
        userService.save(userDto);
        return "login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Helytelen e-mail vagy jelszó!");
        }
        return "login";
    }


    @GetMapping("/loged_in")
    public String userPage(Model model, Principal principal)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "loged_in";
    }

    @GetMapping("/f1")
    public String showTickets(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "f1";
    }

    @GetMapping("/news")
    public String showNews(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "news";
    }

    @GetMapping("/news_ki")
    public String showNewsLogOut() {
        return "news_ki";
    }

    @GetMapping("/f1_ki")
    public String showTicketsLogOut() {
        return "f1_ki";
    }

    @GetMapping("/buy")
    public String getBuyingPage(@ModelAttribute("ticket") TicketDTo ticketDTo, Model model, Principal principal)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "ticket_buying";
    }

    @GetMapping("/f1tablazat")
    public String showF1tablazat()
    {
        return "f1tablazat";
    }

    @PostMapping("/buy")
    public String saveTicket(@ModelAttribute("ticket") TicketDTo ticketDto, Model model) {
        ticketServiceInterface.save(ticketDto);
        model.addAttribute("ticket", ticketDto);
        return "redirect:/f1";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        List<Ticket> tickets = ticketServiceInterface.findTicketsByEmail(principal.getName());
        model.addAttribute("tickets", tickets);

        return "profile";
    }

    @GetMapping("/new_name")
    public String chaneName() {
        return "new_name";
    }

    @PostMapping("/new_name")
    public String updateName(@RequestParam("fullname") String newFullName, Principal principal, Model model) {
        String currentUsername = principal.getName();
        userService.updateFullName(currentUsername, newFullName);
        model.addAttribute("message", "Név sikeresen frissítve!");
        return "redirect:/profile";
    }

    @PostMapping("/profile/delete_ticket")
    public String deleteTicket(@RequestParam("ticketId") Long ticketId, Principal principal) {

        ticketServiceInterface.deleteTicketById(ticketId);
        return "redirect:/profile";
    }
}