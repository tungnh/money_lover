package d2.money.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("indexActive","active");
        return "user/index";
    }
    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("profileActive","active");
        return "user/profile";
    }
    @GetMapping("/setting")
    public String setting(Model model){
        model.addAttribute("settingActive","active");
        return "user/setting";
    }
}

