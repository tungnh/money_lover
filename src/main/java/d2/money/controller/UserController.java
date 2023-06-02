package d2.money.controller;

import d2.money.service.*;
import d2.money.service.dto.LoginDTO;
import d2.money.service.dto.RegisterDTO;
import d2.money.service.dto.UserDTO;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    public final PersistentTokenBasedRememberMeServices rememberMeServices;
    public final UserService userService;
    private final WalletService walletService;
    private final CurrencyService currencyService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;

    public UserController(PersistentTokenBasedRememberMeServices rememberMeServices, UserService userService, WalletService walletService, CurrencyService currencyService, TransactionService transactionService, NotificationService notificationService) {
        this.rememberMeServices = rememberMeServices;
        this.userService = userService;
        this.walletService = walletService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
    }

    @GetMapping("/index")
    public String homePage(HttpSession session, Authentication authentication, Model model) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            model.addAttribute("notification",notificationService.findAllByUser());
            UserDTO user = userDTO.get();
            session.setAttribute("user", user);
            if (walletService.findAll() != null) {
                WalletDTO wallet = (WalletDTO) session.getAttribute("wallet");
                if (wallet != null) {
                    model.addAttribute("listTransaction", transactionService.findTransactionByWalletIdOrReceiveWalletId(wallet.getId()));
                }
                model.addAttribute("listCurrency",currencyService.getAllCurrency());
                session.setAttribute("listWallet", walletService.findAll());
                return "user/index";
            }
            return "redirect:/wallet/add";
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
        Optional<UserDTO> exitUserName = userService.findOneByUsername(userDto.getUsername());
        Optional<UserDTO> exitEmail = userService.findOneByEmail(userDto.getEmail());
        if (exitEmail.isPresent()) {
            model.addAttribute("user", new RegisterDTO());
            model.addAttribute("erroremail", "Email đã tồn tại");
            if (exitUserName.isPresent()) {
                model.addAttribute("user", new RegisterDTO());
                model.addAttribute("errorusername", "Tên đăng nhập đã tồn tại");
            }
            return "/register";
        }
        userService.registerUser(userDto);
        model.addAttribute("registrationSuccess", true);
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String showLoginForm(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userlogin", new LoginDTO());
        model.addAttribute("param", new Object());
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
    }

    @GetMapping("/")
    public String index(Model model) {
        List<WalletDTO> walletDTOList = walletService.findAll();
        if (walletDTOList != null) {
            WalletDTO walletDTO = walletDTOList.get(0);
            Optional<CurrencyDTO> currencyDTO = currencyService.findById(walletDTO.getCurrencyId());
            model.addAttribute("wallet", walletDTO);
            model.addAttribute("currency", currencyDTO);
            model.addAttribute("listWallet", walletService.findAll());
        }
        return "redirect:/wallet/add";
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
    }

    @GetMapping("/setting")
    public String setting(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
        }
        return "user/setting";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO userDTO, Authentication authentication,HttpSession session, Model model) {
        UserDTO updatedUser = userService.update(userDTO, authentication);
        if (updatedUser != null) {
            model.addAttribute("user", updatedUser);
            session.setAttribute("user", updatedUser);
            return "redirect:/user/profile";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/settings")
    public String showSettingsPage(Model model) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = userService.getUserProfile(authentication).orElseThrow(() -> new NotFoundException("User not found"));
        model.addAttribute("user", userDTO);
        return "settings";
    }
}