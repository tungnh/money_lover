package d2.money.controller;

import d2.money.service.UserService;
import d2.money.service.dto.UserDTO;
import org.springframework.security.core.Authentication;
import d2.money.service.CurrencyService;
import d2.money.service.WalletService;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wallet/")
public class WalletController {
    public final UserService userService;
    private final WalletService walletService;
    private final CurrencyService currencyService;

    public WalletController(UserService userService, WalletService walletService, CurrencyService currencyService) {
        this.userService = userService;
        this.walletService = walletService;
        this.currencyService = currencyService;
    }


    @GetMapping("index")
    public String wallet(Model model, Authentication authentication) {
        List<WalletDTO> walletDTOList = walletService.getAllWallet();
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
        }
        if (walletDTOList != null) {
            WalletDTO walletDTO = walletDTOList.get(0);
            Optional<CurrencyDTO> currencyDTO = currencyService.findById(walletDTO.getCurrencyId());
            model.addAttribute("wallet", walletDTO);
            model.addAttribute("currency", currencyDTO);
        }
        return "user/wallet/add";
    }

    @GetMapping("list")
    public String listWallet(Model model) {
        model.addAttribute("listWallet", walletService.getAllWallet());
        return "user/wallet/index";
    }

    @GetMapping("add")
    public String addWallet(Model model) {
        WalletDTO walletDTO = walletService.getAllWallet().get(0);
        model.addAttribute("wallet", walletDTO);
        model.addAttribute("listCurrency", currencyService.getAllCurrency());
        return "user/wallet/add";
    }

    @PostMapping("add")
    public String createWallet(@ModelAttribute WalletDTO walletDTO, Model model) {
        walletService.save(walletDTO);
        model.addAttribute("listWallet", walletService.getAllWallet());
        model.addAttribute("wallet", walletDTO);
        return "user/wallet/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<WalletDTO> walletDTOOptional = walletService.findByWalletId(id);
        if (walletDTOOptional.isPresent()) {
            WalletDTO walletDTO = walletDTOOptional.get();
            model.addAttribute("wallet", walletDTO);
            return "user/wallet/update";
        }
        return "user/index";
    }

    @PostMapping("update")
    public String updateWallet(@ModelAttribute WalletDTO walletDTO, Model model) {
        walletService.update(walletDTO);
        model.addAttribute("listWallet", walletService.getAllWallet());
        model.addAttribute("wallet", walletDTO);
        return "user/wallet/index";
    }

    @GetMapping("delete/{id}")
    public String deleteWallet(@PathVariable int id, Model model) {
        walletService.delete(id);
        model.addAttribute("listWallet", walletService.getAllWallet());
        return "user/wallet/index";
    }
}

