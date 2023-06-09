package d2.money.controller;

import d2.money.service.dto.CurrencyDTO;
import d2.money.service.util.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/currency/")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/index")
    public String currency(Model model) {
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency";
    }

    @GetMapping("/add")
    public String addCurrency(@ModelAttribute CurrencyDTO currencyRequest, Model model) {
        model.addAttribute("currency", new CurrencyDTO());
        return "admin/addCurrency";
    }

    @PostMapping("/add")
    public String createCurrency(@ModelAttribute CurrencyDTO currencyRequest, Model model) {
        currencyService.save(currencyRequest);
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency";
    }
}
