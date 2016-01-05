package ua.alex.source.webtester.components.impl;

import net.sf.oval.Validator;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.alex.source.webtester.service.AdminService;

import java.util.ResourceBundle;

@Component
public class InitSystems implements InitializingBean {

    private static final Logger LOGGER = Logger.getLogger(InitSystems.class);
    @Autowired
    private AdminService adminService;

    @Override
    public void afterPropertiesSet() throws Exception {
        createAdmin();
        setOvalResourceBundle();
    }

    private void setOvalResourceBundle() {
        ResourceBundleMessageResolver resolver = (ResourceBundleMessageResolver) Validator.getMessageResolver();
        resolver.addMessageBundle(ResourceBundle.getBundle("messages/messages"));
    }

    private void createAdmin() {
        adminService.createAdmin();
    }
}
