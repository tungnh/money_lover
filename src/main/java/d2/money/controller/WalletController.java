package d2.money.controller;

import d2.money.service.dto.WalletRequest;
import d2.money.service.util.CurrencyService;
import d2.money.service.util.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WalletController {
    private final WalletService walletService;
    private final CurrencyService currencyService;

    public WalletController(WalletService walletService, CurrencyService currencyService) {
        this.walletService = walletService;
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index(Model model) {

        return "admin/index";
    }

    @GetMapping("/w")
    public String ww() {

        return "admin/wallet";
    }

    @GetMapping("/profile")
    public String profile() {

        return "admin/profile";
    }

    @GetMapping("/s")
    public String addWalet(Model model) {
        model.addAttribute("listwallet", walletService.getAllWallet());
        return "user/wallet";
    }

    @GetMapping("/add")
    public String addWallet(Model model) {
        model.addAttribute("wallet",new WalletRequest());
        model.addAttribute("listCurrency",currencyService.getAllCurrency());
        return "user/addWallet";
    }

    @PostMapping("/addwallet")
    public String createWallet(@ModelAttribute WalletRequest wallet,Model model) {
        walletService.save(wallet);
        model.addAttribute("listwallet", walletService.getAllWallet());
        return "user/wallet";
    }
}
