package d2.money.controller;

import d2.money.service.UserService;
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    public final UserService userService;

    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String wallet(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
            return "user/wallet/index";
        }
        return "user/wallet/index";
    }
}
