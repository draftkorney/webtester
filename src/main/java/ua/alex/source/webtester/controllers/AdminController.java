package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.NewAccount;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.service.AdminService;
import ua.alex.source.webtester.utils.PaginationData;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @Autowired
    protected AdminService adminService;
    @Autowired
    protected AccountService accountService;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "admin/home";
    }

    @RequestMapping(value = "/home/accountsList.html", method = RequestMethod.GET)
    public String showAccountList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer count, Model model) {
        int totalCount = accountService.countUsers();
        PaginationData paginationData = new PaginationData(totalCount, count, page);

        List<Account> accountList = adminService.getUsers(paginationData.getPage(), count);
        model.addAttribute("paginationData", paginationData);
        model.addAttribute("users", accountList);

        return "admin/accountsList";
    }

    @RequestMapping(value = "/addNewAccount.html", method = RequestMethod.GET)
    public String addNewUserPage(Model model) {
        model.addAttribute("newAccount", new NewAccount());
        return "admin/addNewAccount";
    }

    @RequestMapping(value = "/add_new_account", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute NewAccount newAccount, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/addNewAccount";
        }

        adminService.addNewAccount(newAccount);
        return "redirect:/admin/home/accountsList.html";
    }


}
