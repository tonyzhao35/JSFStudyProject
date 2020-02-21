package com.jsf.demo.bean;

import com.jsf.demo.common.UrlConstants;
import com.jsf.demo.util.FacesContextUtils;
import lombok.Data;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class UserLoginForm implements Serializable {
    private static final long serialVersionUID = 1L;

    public interface LoginGroup {

    }

    public interface PWDChangeGroup {

    }

    @Size(min = 4, max = 6, message = "大小应该在4-6位之间", groups = LoginGroup.class)
    @NotNull
    private String userId;

    @Size(min = 4, max = 6, message = "大小应该在4-6位之间", groups = LoginGroup.class)
    @NotNull
    private String password;

    @PostConstruct
    public void init() {
        System.out.println("UserLoginForm.init()");
        setUserId("1001");
        setPassword("1234");
    }

    public String login() {
        // 手動Validator
        if (!FacesContextUtils.validate(this, LoginGroup.class)) {
            return null;
        }

        /* 画面遷移*/
        return UrlConstants.MA000101_PAGE.concat(UrlConstants.FACES_REDIRECT_TRUE);
    }

}
