package szfm.krankenwagenracing.admin_user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import szfm.krankenwagenracing.admin_user.dto.TicketDTo;
import szfm.krankenwagenracing.admin_user.dto.UserDto;
import szfm.krankenwagenracing.admin_user.model.User;
import szfm.krankenwagenracing.admin_user.service.TicketService;
import szfm.krankenwagenracing.admin_user.service.TicketServiceInterface;
import szfm.krankenwagenracing.admin_user.service.UserService;

import java.security.Principal;

@Controller
public class UserController
{
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketServiceInterface ticketServiceInterface;

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
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model)
    {
        userService.save(userDto);
        model.addAttribute("message", "Sikeres regisztráció");
        return "register";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/loged_in")
    public String userPage(Model model, Principal principal)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "loged_in";
    }

    @GetMapping("/buy")
    public String getBuyingPage(@ModelAttribute("ticket") TicketDTo ticketDTo)
    {
        return "ticket_buying";
    }

    @GetMapping("/tickets")
    public String getTicketPage()
    {
        return "f1";
    }

    @PostMapping("/buy")
    public String saveTicket(@ModelAttribute("ticket") TicketDTo ticketDto, Model model)
    {
        ticketServiceInterface.save(ticketDto);
        model.addAttribute("message", "Sikeres vásárlás");
        return "ticket_buying";
    }

}