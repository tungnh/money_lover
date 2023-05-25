package d2.money.controller;

import d2.money.service.dto.CurrencyDTO;
import d2.money.service.CurrencyService;
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

    @GetMapping("index")
    public String currency(Model model) {
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency/index";
    }
    @GetMapping("add")
    public String addCurrency(Model model) {
        model.addAttribute("currency", new CurrencyDTO());
        return "admin/currency/add";
    }

    @PostMapping("add")
    public String createCurrency(@ModelAttribute CurrencyDTO currencyDTO, Model model) {
        currencyService.save(currencyDTO);
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency/index";
    }
}
