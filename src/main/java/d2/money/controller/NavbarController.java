package d2.money.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NavbarController {
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("indexActive","active");
        return "user/index";
    }
    @GetMapping("wallet")
    public String wallet(Model model){
        model.addAttribute("walletActive","active");
        return "user/wallet";
    }
    @GetMapping("profile")
    public String profile(Model model){
        model.addAttribute("profileActive","active");
        return "user/profile";
    }
    @GetMapping("setting")
    public String setting(Model model){
        model.addAttribute("settingActive","active");
        return "user/setting";
    }

}
