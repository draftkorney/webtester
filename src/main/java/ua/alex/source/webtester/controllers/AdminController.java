package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.AccountForm;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.service.AdminService;
import ua.alex.source.webtester.utils.PaginationData;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
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
        model.addAttribute("adminPaginationData", paginationData);
        model.addAttribute("users", accountList);

        return "admin/accountList";
    }

    @RequestMapping(value = {"/addNewAccount.html", "/editAccount.html"}, method = RequestMethod.GET)
    public String addNewUserPage(@RequestParam(required = false) Long idAccount, Model model) {
        AccountForm account;

        if (idAccount == null) {
            account = new AccountForm();
        } else {
            account = accountService.convertToAccountForm(idAccount);
        }

        model.addAttribute("accountForm", account);
        return "admin/addnewaccount";
    }

    @RequestMapping(value = "/action_with_account", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute @Valid AccountForm accountForm, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/addnewaccount";
        }

        adminService.addNewAccount(accountForm);
        return "redirect:/admin/home/accountsList.html";
    }


}
