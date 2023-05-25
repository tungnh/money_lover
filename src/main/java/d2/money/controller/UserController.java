package d2.money.controller;

import d2.money.service.dto.LoginDTO;
import d2.money.service.dto.RegisterDTO;
import d2.money.service.util.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    public final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

/*    @GetMapping("/index")
    public String index() {
        return "/user/index";
    }*/
    @GetMapping("/index")
    public String homePage(Model model, Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getName();

        //lấy đối tượng Authentication thông qua tham số của phương thức home()
        if (authentication != null && authentication.isAuthenticated()) {
            //kiểm tra xem người dùng đã được xác thực hay chưa
            String username = authentication.getName();
            model.addAttribute("username", username);
            //Authentication và truyền vào biến model để hiển thị trên giao diện
        }
        return "/user/index";
    }
    @GetMapping()
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegisterDTO userDto, Model model) {
        System.out.println(userDto.getBirthday());
        userService.registerUser(userDto);
        model.addAttribute("registrationSuccess", true);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new LoginDTO());
        return "login"; // Trả về tên của tệp HTML đăng nhập
    }
}


