package d2.money.controller;

import d2.money.service.FriendService;
import d2.money.service.NotificationService;
import d2.money.service.UserService;
import d2.money.service.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/friend/")
public class FriendController {
    private final FriendService friendService;
    private final UserService userService;
    private final NotificationService notificationService;

    public FriendController(FriendService friendService, UserService userService, NotificationService notificationService) {
        this.friendService = friendService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping("index")
    public String index(Model model){
        List<UserDTO> friendList=userService.findAllUser();
        model.addAttribute("notification",notificationService.findAllByUser());
        model.addAttribute("listFriend",friendList);
        return "user/friend/index";
    }
    @GetMapping("add/{id}")
    public String addFriend(@PathVariable int id,Model model){
        friendService.save(id);
        List<UserDTO> friendList=userService.findAllUser();
        model.addAttribute("listFriend",friendList);
        return "user/friend/index";
    }
}