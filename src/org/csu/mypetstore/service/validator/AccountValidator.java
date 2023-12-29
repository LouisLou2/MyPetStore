package org.csu.mypetstore.service.validator;

import org.csu.mypetstore.repository.RedisCache;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.utils.FormatUtil;

import java.util.HashMap;
import java.util.Map;

public class AccountValidator {

    public static boolean checkImageCode(String code, String JSESSIONID) {
        String imageCode = RedisCache.getImageCode(JSESSIONID);
        if (imageCode == null) {
            return false;
        }
        if (imageCode.equalsIgnoreCase(code)) {
            return true;
        }
        return false;
    }
    public static boolean checkEmailCode(String code, String email) {
        String emailCode = RedisCache.getEmailCode(email);
        if (emailCode == null) {
            return false;
        }
        if (emailCode.equals(code)) {
            return true;
        }
        return false;
    }
    public static Map<String,String> CheckRegisterParams(Map<String,Object> params){
        HashMap<String,String> errorinfo=new HashMap<>();
        for(Map.Entry<String,Object> entry:params.entrySet()){
            String key=entry.getKey();
            Object value=entry.getValue();
            if(value==null|| value.toString().isEmpty()){
                errorinfo.put(key,"can not be empty");
            }
        }
        String email= (String) params.get("email");
        String phone= (String) params.get("phone");
        if(email!=null){
            if(!FormatUtil.checkEmail(email)){
                errorinfo.put("email","invalid email address");
            }
        }
        if(phone!=null){
            if(!FormatUtil.checkPhone(phone)){
                errorinfo.put("phone","invalid phone number");
            }
        }
        String username= (String) params.get("username");
        if(username!=null){
            if(AccountService.isExist(username)){
                errorinfo.put("username","username already exist");
            }
        }
        if(email!=null){
            if(AccountService.isEmailExist(email)){
                errorinfo.put("email","email already exist");
            }
        }
        if(phone!=null){
            if(AccountService.isPhoneExist(phone)){
                errorinfo.put("phone","phone already exist");
            }
        }
        return errorinfo;
    }
}
