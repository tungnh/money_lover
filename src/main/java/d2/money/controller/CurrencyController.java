package d2.money.controller;

import d2.money.service.dto.CurrencyDTO;
import d2.money.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<CurrencyDTO> optionalCurrencyDTO = currencyService.findById(id);
        if (optionalCurrencyDTO.isPresent()) {
            CurrencyDTO currencyDTO = optionalCurrencyDTO.get();
            model.addAttribute("currency", currencyDTO);
            return "admin/currency/update";
        }
        return "user/index";
    }

    @PostMapping("update")
    public String updateCurrency(@ModelAttribute CurrencyDTO currencyDTO, Model model) {
        currencyService.update(currencyDTO);
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency/index";
    }

    @GetMapping("delete/{id}")
    public String deleteCurrency(@PathVariable int id, Model model) {
        currencyService.delete(id);
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency/index";
    }
}
