package ua.alex.source.webtester.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This class provides application properties on JSP pages
 */
@Service("props")
public class ApplicationProps {

    @Value("${base.site}")
    private String baseSite;

    public String getBaseSite() {
        return baseSite;
    }

}
