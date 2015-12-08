package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.alex.source.webtester.forms.NewAccount;
import ua.alex.source.webtester.service.AdminService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @Autowired
    protected AdminService adminService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "admin/home";
    }

    @RequestMapping(value = "/home/accountsList.html", method = RequestMethod.GET)
    public String showAccountList() {
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
        return "admin/accountsList";
    }


}
