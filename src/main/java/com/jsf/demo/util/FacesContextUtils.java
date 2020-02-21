package com.jsf.demo.util;

import com.jsf.demo.common.SpringContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.BeanValidator;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class FacesContextUtils {

    /**
     * FacesContextのメッセージ定義取得処理
     *
     * @param key メッセージコード
     * @return メッセージ
     */
    public static String getMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "i18n");
        return bundle.containsKey(key) ? bundle.getString(key) : key;
    }

    /**
     * 短項目チェックのバリデーション
     *
     * @param target          対象フォーム
     * @param validationHints バリデーショングループ
     * @return バリデーション結果
     */
    public static boolean validate(Object target, Object... validationHints) {
        BindingResult bindingResult = new DataBinder(target).getBindingResult();
        SpringContext.getInstance().getValidator().validate(target, bindingResult, validationHints);
        if (bindingResult.hasErrors()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String msgFormat = getMessage(BeanValidator.MESSAGE_ID);
            for (ObjectError error : bindingResult.getAllErrors()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        MessageFormat.format(msgFormat,
                                error.getDefaultMessage(),
                                getMessage(((FieldError) error).getField())),
                        null);
                facesContext.addMessage(null, message);
            }
            return false;
        }
        return true;
    }
}
