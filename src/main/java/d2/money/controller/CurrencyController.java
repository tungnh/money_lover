package d2.money.controller;

import d2.money.service.dto.CurrencyRequest;
import d2.money.service.util.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public String currency(Model model){
        model.addAttribute("listcurrency",currencyService.getAllCurrency());
        return "admin/currency";
    }


    @GetMapping("/addCurrency")
    public String addCurrency(@ModelAttribute CurrencyRequest currencyRequest, Model model){
        model.addAttribute("currency",new CurrencyRequest());
        return "admin/addCurrency";
    }
    @PostMapping("/createCurrency")
    public String createCurrency(@ModelAttribute CurrencyRequest currencyRequest, Model model){
        currencyService.save(currencyRequest);
        model.addAttribute("listcurrency",currencyService.getAllCurrency());
        return "admin/currency";
    }
}
