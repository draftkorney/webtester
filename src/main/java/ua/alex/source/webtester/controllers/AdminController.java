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
import ua.alex.source.webtester.forms.AccountForm;
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
        model.addAttribute("adminPaginationData", paginationData);
        model.addAttribute("users", accountList);
        model.addAttribute("adminActive", "active");

        return "admin/accountList";
    }

    @RequestMapping(value = {"/addNewAccount.html", "/editAccount.html"}, method = RequestMethod.GET)
    public String addNewUserPage(@RequestParam(required = false) Long idAccount, Model model) {
        AccountForm account;
        String header;

        if (idAccount == null) {
            account = new AccountForm();
            header = "Add a new account";
        } else {
            account = accountService.convertToAccountForm(idAccount);
            header = "Edit account";
        }

        model.addAttribute("accountForm", account);
        model.addAttribute("headerAction", header);
        return "admin/addnewaccount";
    }

    @RequestMapping(value = "/action_with_account", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute @Valid AccountForm accountForm, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/addnewaccount";
        }

        adminService.saveOrUpdateAccount(accountForm);
        return "redirect:/admin/home/accountsList.html";
    }

    @RequestMapping(value = "/update_user_activity", method = RequestMethod.POST)
    public String changeUserActivity(@RequestParam Long idAccount) {

        adminService.changeUserActivity(idAccount);
        return "redirect:/admin/home/accountsList.html";
    }


}
