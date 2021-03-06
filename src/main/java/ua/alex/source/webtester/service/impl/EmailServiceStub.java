package ua.alex.source.webtester.service.impl;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import ua.alex.source.webtester.components.ApplicationProps;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;
import ua.alex.source.webtester.service.EmailService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Service("emailService")
public class EmailServiceStub implements EmailService {
    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ApplicationProps props;

    @Value("${mail.user.from}")
    private String fromEmail;

    @Override
    public void sendVerificationEmail(final AccountRegistration user) {
        String templateBodyLocation = "template/registration.vm";

        String title = messageSource.getMessage("registration.header", new Object[]{}, getLocale());

        Map<String, Object> param = new HashMap<>();

        param.put("param", new HashMap<String, Object>() {{
            this.put("firstName", user.getAccount().getFio());
            this.put("hashCode", user.getHash());
            this.put("springMacroRequestContext", new RequestContext(getRequest()));
        }});

        param.put("pathTemplate", templateBodyLocation);
        param.put("subject", title);

        LOGGER.info("Send confirm email...");
        sendEmail(param, fromEmail, user.getAccount().getEmail());
        LOGGER.info("email was sent successfully...");
    }

    @Override
    public void confirmNewUser(Account account) {
        String templateBodyLocation = "template/sendpassword.vm";

        String title = messageSource.getMessage("registration.header", new Object[]{}, getLocale());

        Map<String, Object> param = new HashMap<>();

        param.put("param", new HashMap<String, Object>() {{
            this.put("login", account.getLogin());
            this.put("firstName", account.getFio());
            this.put("password", account.getPassword());
            this.put("springMacroRequestContext", new RequestContext(getRequest()));
        }});

        param.put("pathTemplate", templateBodyLocation);
        param.put("subject", title);

        sendEmail(param, fromEmail, account.getEmail());
    }

    @Override
    public void sendNewEmailOrLogin(Account account, boolean isNewLogin, boolean isNewEmail) {
        String templateBodyLocation = "template/sendnewlogin.vm";

        String title = messageSource.getMessage("email.admin.change.header", new Object[]{}, getLocale());

        Map<String, Object> param = new HashMap<>();

        param.put("param", new HashMap<String, Object>() {{
            this.put("firstName", account.getFio());
            this.put("isNewLogin", isNewLogin);
            this.put("isNewEmail", isNewEmail);
            this.put("springMacroRequestContext", new RequestContext(getRequest()));
            if (isNewEmail) {
                this.put("email", account.getEmail());
            }
            if (isNewLogin) {
                this.put("login", account.getLogin());
            }
        }});

        param.put("pathTemplate", templateBodyLocation);
        param.put("subject", title);

        sendEmail(param, fromEmail, account.getEmail());
    }

    @Override
    public void sendForgotPassword(Account account) {
        String templateBodyLocation = "template/forgotpassword.vm";

        String title = messageSource.getMessage("email.forgot.password.header", new Object[]{}, getLocale());

        Map<String, Object> param = new HashMap<>();

        param.put("param", new HashMap<String, Object>() {{
            this.put("login", account.getLogin());
            this.put("firstName", account.getFio());
            this.put("password", account.getPassword());
            this.put("springMacroRequestContext", new RequestContext(getRequest()));
        }});

        param.put("pathTemplate", templateBodyLocation);
        param.put("subject", title);

        sendEmail(param, fromEmail, account.getEmail());
    }

    @Override
    public void sendGeneratedPasswordToEmail() {

    }

    private void sendEmail(Map<String, Object> param, String from, String... to) {
        if (to == null) return;

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(to);
            message.setFrom(from); // could be parameterized...
            message.setSubject((String) param.get("subject"));

            Map<String, Object> map = (Map<String, Object>) param.get("param");
            map.put("props", props);

            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, (String) param.get("pathTemplate"), "UTF-8", map);
            message.setText(text, true);
        };

        LOGGER.info("Sending email to: " + Arrays.toString(to));
        this.sender.send(preparator);
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
        return sra.getRequest();
    }
}
