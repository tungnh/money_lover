package d2.money.controller;

import d2.money.service.NotificationService;
import d2.money.service.UserService;
import d2.money.service.dto.NotificationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notification/")
public class NotificationController {
    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("listNotification", notificationService.findAll());
        return "admin/notification/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("listUser", userService.findAll());
        return "admin/notification/add";
    }

    @PostMapping("add")
    public String create(@ModelAttribute NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
        return "redirect:/notification/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        notificationService.delete(id);
        return "redirect:/notification/index";
    }
}