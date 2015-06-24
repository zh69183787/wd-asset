package com.wonders.framework.util;

import javax.servlet.ServletContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Downpour
 *
 */
public class SpringUtil {

    /**
     * 得到springBean
     * 
     * @param context
     * @param beanName
     * @return
     */
    public static Object getSpringBean(ServletContext context, String beanName) {
        return WebApplicationContextUtils.getWebApplicationContext(context)
                .getBean(beanName);
    }
}
