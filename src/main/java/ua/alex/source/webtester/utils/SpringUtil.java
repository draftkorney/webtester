package ua.alex.source.webtester.utils;

import org.springframework.web.context.ContextLoader;

import java.util.Locale;

/**
 * Created by Alex-II on 13.12.2015.
 */
public class SpringUtil {

    public static Object getBean(String beanName) {
        return ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
    }

    public static Object getBean(Class requiredType) {

        return ContextLoader.getCurrentWebApplicationContext().getBean(requiredType);
    }

    public static String getMessages(String code, Locale locale) {

        return ContextLoader.getCurrentWebApplicationContext().getMessage(code, null, locale);
    }

}
