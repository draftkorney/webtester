package ua.alex.source.webtester.components.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.dao.AccountRoleDao;
import ua.alex.source.webtester.dao.RoleDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRole;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.service.AdminService;
import ua.alex.source.webtester.utils.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class InitSystems implements InitializingBean {

    private static final Logger LOGGER = Logger.getLogger(InitSystems.class);
    @Autowired
    private AdminService adminService;

    @Override
    public void afterPropertiesSet() throws Exception {
        createAdmin();
    }

    private void createAdmin() {
        adminService.createAdmin();
    }
}
