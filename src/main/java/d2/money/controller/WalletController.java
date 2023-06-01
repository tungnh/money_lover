package d2.money.controller;

import d2.money.config.FileUploadUtil;
import d2.money.service.*;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.dto.TransactionDTO;
import d2.money.service.dto.UserDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/wallet/")
public class WalletController {
    public final UserService userService;
    private final WalletService walletService;
    private final CurrencyService currencyService;
    private final TransactionService transactionService;
    private final NotificationService notificationService;

    public WalletController(UserService userService, WalletService walletService, CurrencyService currencyService, TransactionService transactionService, NotificationService notificationService) {
        this.userService = userService;
        this.walletService = walletService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.notificationService = notificationService;
    }

    @GetMapping("index")
    public String wallet(HttpSession session, Model model) {
        List<WalletDTO> walletDTOList = walletService.findAll();
        if (walletDTOList != null) {
            session.setAttribute("listWallet", walletDTOList);
            model.addAttribute("notification",notificationService.findAllByUser());
            model.addAttribute("listWallet", walletDTOList);
            return "user/wallet/index";
        }
        return "redirect:/wallet/add";

    }

    @GetMapping("index/{id}")
    public String walletById(@PathVariable int id, HttpSession session, Model model) {
        Optional<WalletDTO> walletDTOList = walletService.findById(id);
        Optional<CurrencyDTO> currencyDTO = currencyService.findById(walletDTOList.get().getCurrencyId());
        session.setAttribute("wallet", walletDTOList.get());
        session.setAttribute("currency",currencyDTO.get());
        model.addAttribute("transactionRecent", transactionService.findTransactionByWalletIdOrReceiveWalletId(id));
        return "/user/index";
    }

    @GetMapping("add")
    public String addWallet(Model model) {
        model.addAttribute("listCurrency", currencyService.getAllCurrency());
        return "user/wallet/add";
    }

    @PostMapping("add")
    public String createWallet(@RequestParam("file") MultipartFile multipartFile,Authentication authentication, @ModelAttribute WalletDTO walletDTO, Model model, HttpSession session) throws IOException {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        String uploadDir ="";
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            uploadDir="src/main/resources/static/user-photos/" +user.getId();
        }
        String fileName = multipartFile.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + "-" + fileName;
        FileUploadUtil.saveFile(uploadDir, storedFileName, multipartFile);
        walletDTO.setImage(storedFileName);
        walletService.save(walletDTO);
        session.setAttribute("wallet", walletDTO);
        Optional<CurrencyDTO> currencyDTO = currencyService.findById(walletDTO.getCurrencyId());
        session.setAttribute("currency",currencyDTO.get());
        return "redirect:/wallet/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<WalletDTO> walletDTOOptional = walletService.findById(id);
        if (walletDTOOptional.isPresent()) {
            WalletDTO walletDTO = walletDTOOptional.get();
            Optional<CurrencyDTO> currency = currencyService.findById(walletDTO.getCurrencyId());
            model.addAttribute("wallet", walletDTO);
            model.addAttribute("currency", currency.get());
            model.addAttribute("listCurrency", currencyService.getAllCurrency());
            return "user/wallet/update";
        }
        return "user/index";
    }

    @PostMapping("edit/{id}")
    public String updateWallet(@PathVariable int id, @ModelAttribute WalletDTO walletDTO, Model model, HttpSession session) {
        walletDTO.setId(id);
        walletService.update(walletDTO);
        session.setAttribute("wallet", walletDTO);
        Optional<CurrencyDTO> currencyDTO = currencyService.findById(walletDTO.getCurrencyId());
        session.setAttribute("currency",currencyDTO.get());
        return "redirect:/wallet/index";
    }

    @GetMapping("delete/{id}")
    public String deleteWallet(@PathVariable int id, HttpSession session) {
        Optional<WalletDTO> walletDTOOptional = walletService.findById(id);
        List<TransactionDTO> transactionDTOS = transactionService.findByWalletId(walletDTOOptional.get().getId());
        if (transactionDTOS != null) {
            for (TransactionDTO tr : transactionDTOS) {
                transactionService.delete(tr.getId());
            }
        }
        walletService.delete(id);
        session.setAttribute("wallet", walletService.findAll().get(0));
        return "redirect:/wallet/index";
    }
}