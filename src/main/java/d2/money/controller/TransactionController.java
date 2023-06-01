package d2.money.controller;

import d2.money.service.*;
import d2.money.service.dto.TransactionDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/transaction/")
public class TransactionController {
    private final TransactionService transactionService;
    public final UserService userService;
    private final WalletService walletService;
    private final CategoryService categoryService;

    private final FriendService friendService;
    private final NotificationService notificationService;

    public TransactionController(TransactionService transactionService, UserService userService, WalletService walletService, CategoryService categoryService, FriendService friendService, NotificationService notificationService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.walletService = walletService;
        this.categoryService = categoryService;
        this.friendService = friendService;
        this.notificationService = notificationService;
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        WalletDTO wallet = (WalletDTO) session.getAttribute("wallet");
        model.addAttribute("notification",notificationService.findAllByUser());
        model.addAttribute("listTransaction", transactionService.findByDayBetweenAndWalletId(wallet.getId()));
        session.setAttribute("listWallet", walletService.findAll());
        model.addAttribute("listWallet", walletService.findAll());
        return "user/transaction/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("transaction", new TransactionDTO());
        model.addAttribute("listFriend", friendService.findAll());
        model.addAttribute("listWallet", walletService.findAll());
        model.addAttribute("listCategory", categoryService.findAll());
        return "user/transaction/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TransactionDTO transactionDTO,HttpSession session) {
        transactionService.save(transactionDTO);
        Optional<WalletDTO> wallet=walletService.findById(transactionDTO.getWalletTransferId());
        session.setAttribute("wallet", wallet.get());
        return "redirect:/transaction/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<TransactionDTO> transactionDTOOptional = transactionService.findById(id);
        TransactionDTO transactionDTO = transactionDTOOptional.get();
        model.addAttribute("transaction", transactionDTO);
        model.addAttribute("listWallet", walletService.findAll());
        if (transactionDTO.getCategoryId() != null && transactionDTO.getReceivingWalletId() == null) {
            model.addAttribute("listFriend", friendService.findAll());
            model.addAttribute("listCategory", categoryService.findAll());
            return "user/transaction/update";
        }
        Optional<WalletDTO> walletReceive = walletService.findById(transactionDTO.getReceivingWalletId());
        model.addAttribute("walletReceive", walletReceive.get());
        return "user/transaction/updateTransfer";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable int id,HttpSession session ,@ModelAttribute TransactionDTO transactionDTO) {
        transactionDTO.setId(id);
        transactionService.update(transactionDTO);
        Optional<WalletDTO> wallet=walletService.findById(transactionDTO.getWalletTransferId());
        session.setAttribute("wallet", wallet.get());
        return "redirect:/transaction/index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id,HttpSession session) {
        Optional<TransactionDTO> transactionDTOOptional=transactionService.findById(id);
        Optional<WalletDTO> wallet=walletService.findById(transactionDTOOptional.get().getWalletTransferId());
        session.setAttribute("wallet", wallet.get());
        transactionService.delete(id);
        return "redirect:/transaction/index";
    }

    @GetMapping("transfer")
    public String transfer(Model model) {
        model.addAttribute("listWallet", walletService.findAll());
        return "user/transaction/transfer";
    }
}