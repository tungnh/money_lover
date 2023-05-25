package d2.money.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    @GetMapping("/index")
    public String wallet(Model model){
        model.addAttribute("walletActive","active");
        return "user/wallet/index";
    }
}
