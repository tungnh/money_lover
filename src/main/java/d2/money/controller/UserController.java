package d2.money.controller;

import d2.money.service.CurrencyService;
import d2.money.service.WalletService;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
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
    public String profile(Model model){
        return "user/profile";
    }
    @GetMapping("/setting")
    public String setting(Model model){
        return "user/setting";
    }
}

