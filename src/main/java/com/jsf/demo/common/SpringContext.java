package com.jsf.demo.common;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.SmartValidator;

@Component
public class SpringContext implements ApplicationContextAware {

    @Autowired
    @Getter
    private SmartValidator validator;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static SpringContext getInstance() {
        return applicationContext.getBean(SpringContext.class);
    }
}
