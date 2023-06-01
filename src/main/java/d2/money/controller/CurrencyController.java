package d2.money.controller;

import d2.money.service.NotificationService;
import d2.money.service.TransactionService;
import d2.money.service.WalletService;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.CurrencyService;
import d2.money.service.dto.TransactionDTO;
import d2.money.service.dto.WalletDTO;
import d2.money.service.mapper.CurrencyMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/currency/")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final CurrencyMapper currencyMapper;
    private final NotificationService notificationService;
    private final WalletService walletService;
    private final TransactionService transactionService;

    public CurrencyController(CurrencyService currencyService, CurrencyMapper currencyMapper, NotificationService notificationService, WalletService walletService, TransactionService transactionService) {
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
        this.notificationService = notificationService;
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    @GetMapping("index")
    public String currency(Model model) {
        model.addAttribute("notification",notificationService.findAllByUser());
        model.addAttribute("listcurrency", currencyMapper.toEntity(currencyService.getAllCurrency()));
        return "admin/currency/index";
    }

    @GetMapping("add")
    public String addCurrency(Model model) {
        model.addAttribute("currency", new CurrencyDTO());
        return "admin/currency/add";
    }

    @PostMapping("add")
    public String createCurrency(@ModelAttribute CurrencyDTO currencyDTO, Model model) {
        Optional<CurrencyDTO> optionalCurrencyDTO = currencyService.findByName(currencyDTO.getName());
        if (!optionalCurrencyDTO.isPresent()) {
            currencyService.save(currencyDTO);
            model.addAttribute("listcurrency", currencyService.getAllCurrency());
            return "admin/currency/index";
        } else {
            model.addAttribute("err", "Tên tiền tệ đã tồn tại!");
            return "admin/currency/add";
        }
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
    @PostMapping("edit/{id}")
    public String updateCurrency(@PathVariable int id, @ModelAttribute CurrencyDTO currencyDTO, Model model) {
        Optional<CurrencyDTO> optionalCurrencyDTO = currencyService.findByName(currencyDTO.getName());
        CurrencyDTO currencyDTO1 = optionalCurrencyDTO.get();
        if (currencyDTO1 == null || currencyDTO1.getId() == id) {
            currencyDTO.setId(id);
            currencyService.update(currencyDTO);
            model.addAttribute("listcurrency", currencyService.getAllCurrency());
            return "admin/currency/index";
        } else {
            model.addAttribute("err", "Tên tiền tệ đã tồn tại!");
            return "admin/currency/update";
        }
    }
    @GetMapping("delete/{id}")
    public String deleteCurrency(@PathVariable int id, Model model) {
        Optional<CurrencyDTO> currencyDTOOptional =currencyService.findById(id);
        List<WalletDTO> walletDTOList = walletService.findByCurrencyId(currencyDTOOptional.get().getId());
        if (walletDTOList != null) {
            for (WalletDTO wa : walletDTOList) {
                Optional<WalletDTO> walletDTOOptional = walletService.findById(wa.getId());
                List<TransactionDTO> transactionDTOS = transactionService.findByWalletId(walletDTOOptional.get().getId());
                if (transactionDTOS != null) {
                    for (TransactionDTO tr : transactionDTOS) {
                        transactionService.delete(tr.getId());
                    }
                }
                walletService.delete(wa.getId());
            }
        }
        currencyService.delete(id);
        model.addAttribute("listcurrency", currencyService.getAllCurrency());
        return "admin/currency/index";
    }
}