package ua.alex.source.webtester.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.alex.source.webtester.ApplicationConstants;
import ua.alex.source.webtester.controllers.AbstractController;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.forms.SignUpForm;
import ua.alex.source.webtester.security.CurrentAccount;
import ua.alex.source.webtester.security.SecurityUtils;

import javax.validation.Valid;


@Controller
public class CommonControler extends AbstractController implements InitializingBean {

    private final Map<Integer, String> redirects = new HashMap<Integer, String>();

    @Override
    public void afterPropertiesSet() throws Exception {
        redirects.put(ApplicationConstants.ADMIN_ROLE, "/admin/home");
        redirects.put(ApplicationConstants.ADVANCED_TUTOR_ROLE, "/advanced_tutor/home");
        redirects.put(ApplicationConstants.TUTOR_ROLE, "/tutor/home");
        redirects.put(ApplicationConstants.STUDENT_ROLE, "/home");
    }

    protected void initRoles(Model model) {
        List<Role> roles = commonService.listAllRoles();
        model.addAttribute("roles", roles);
    }

    @RequestMapping(value = {"/login", "/loginFailed"}, method = RequestMethod.GET)
    public String showLogin(Model model) {
        initRoles(model);
        return "login";
    }

    @RequestMapping(value = "/create_new_account", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute SignUpForm signUpForm, BindingResult result, Model model) throws InvalidUserInputException {
        System.out.println(signUpForm.toString());

        if (result.hasErrors()) {
            return "registration";
        }

        commonService.signUp(signUpForm);
        return "redirect:" + redirects.get(ApplicationConstants.STUDENT_ROLE);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showSignUp(Model model) throws InvalidUserInputException {
        model.addAttribute("signUpForm",new SignUpForm());
        return "registration";
    }

    @RequestMapping(value = {"/myInfo"}, method = RequestMethod.GET)
    public String myInfo(Model model) {
        CurrentAccount currentAccount = SecurityUtils.getCurrentAccount();
        return "redirect:" + redirects.get(currentAccount.getRole());
    }
}
