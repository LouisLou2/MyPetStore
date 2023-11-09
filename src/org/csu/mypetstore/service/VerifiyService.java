package org.csu.mypetstore.service;

import freemarker.template.TemplateException;
import org.csu.mypetstore.constant.EmailSubjectEnum;
import org.csu.mypetstore.constant.TemplateVault;
import org.csu.mypetstore.utils.EmailUtil;
import org.csu.mypetstore.utils.TemplateUtil;

import java.io.IOException;
import java.util.Map;

public class VerifiyService {
    public static boolean SendEmailVerifyCode(String email,String code){
        boolean res=false;
        try{
            String content= TemplateUtil.MakeProduct(Map.of("code",code), TemplateVault.EMAIL_VERIFY);
            EmailUtil.sendEmail(email, EmailSubjectEnum.EMAIL_VERIFY,content);
            res=true;
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    //手机号验证
    public static boolean SendPhoneVerifyCode(String phone,String code){
        boolean res=false;
        try{
            String content= TemplateUtil.MakeProduct(Map.of("code",code), TemplateVault.PHONE_VERIFY);
            EmailUtil.sendEmail(phone, EmailSubjectEnum.PHONE_VERIFY,content);
            res=true;
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
