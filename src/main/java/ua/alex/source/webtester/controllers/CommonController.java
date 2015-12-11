package ua.alex.source.webtester.controllers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.alex.source.webtester.ApplicationConstants;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.forms.SignUpForm;
import ua.alex.source.webtester.security.CurrentAccount;
import ua.alex.source.webtester.security.SecurityUtils;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CommonController extends AbstractController implements InitializingBean {

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

    @RequestMapping(value = "/confirmationEmail", method = RequestMethod.GET)
    public String registration(@RequestParam String hashCode) throws InvalidUserInputException {
        commonService.activateAccount(hashCode);
        return "confirmemail";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showSignUp(Model model) throws InvalidUserInputException {
        model.addAttribute("signUpForm", new SignUpForm());
        return "registration";
    }

    @RequestMapping(value = {"/myInfo"}, method = RequestMethod.GET)
    public String myInfo(Model model) {
        CurrentAccount currentAccount = SecurityUtils.getCurrentAccount();
        return "redirect:" + getHomeUrl(currentAccount.getRoles());
    }

    private String getHomeUrl(List<Integer> roles) {
        Collections.sort(roles);
        return redirects.get(roles.get(0));
    }
}
