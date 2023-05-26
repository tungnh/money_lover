package d2.money.controller;

import d2.money.service.UserService;
import d2.money.service.dto.LoginDTO;
import d2.money.service.dto.RegisterDTO;
import d2.money.service.dto.UserDTO;
import d2.money.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import d2.money.service.CurrencyService;
import d2.money.service.WalletService;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PersistentTokenBasedRememberMeServices rememberMeServices;
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String homePage(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
            return "user/index";
        }
        return "/user/index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDto, Model model) {
        try {
            userService.registerUser(userDto);
            model.addAttribute("registrationSuccess", true);
            return "redirect:/login";
        } catch (UserServiceImp.EmailExistsException e) {
            model.addAttribute("error", "Email đã tồn tại");
            return "/register";
        } catch (UserServiceImp.UsernameExistsException e) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "/register";
        }
    }

    @GetMapping("/logout")
    public String showLoginForm() {
        return "/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new LoginDTO());
        model.addAttribute("param", new Object()); // Đối tượng tùy ý để truyền giá trị của tham số remember-me vào View
        return "login";
    }

    @PostMapping("/loginn")
    public String login(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        request.getSession().setAttribute("user", authentication.getPrincipal());
        String rememberMe = request.getParameter("remember-me");
        if (rememberMe != null && rememberMe.equals("true")) {
            rememberMeServices.loginSuccess(request, response, authentication);
        }
        return "user/index";
    private final WalletService walletService;
    private final CurrencyService currencyService;

    public UserController(WalletService walletService, CurrencyService currencyService) {
        this.walletService = walletService;
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<WalletDTO> walletDTOList=walletService.getAllWallet();
        if (walletDTOList!=null) {
            WalletDTO walletDTO=walletDTOList.get(0);
            Optional<CurrencyDTO> currencyDTO = currencyService.findById(walletDTO.getCurrencyId());
            model.addAttribute("wallet", walletDTO);
            model.addAttribute("currency", currencyDTO);
            model.addAttribute("listWallet",walletService.getAllWallet());
            return "user/index";
        }
        model.addAttribute("listCurrency",currencyService.getAllCurrency());
        return "user/wallet/add";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
            return "user/profile";
        }
        return "redirect:/user/login";

    public String profile(Model model){
        return "user/profile";
    }

    @GetMapping("/setting")
    public String setting(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
            return "user/setting";
        }

    public String setting(Model model){
        return "user/setting";
    }
}


