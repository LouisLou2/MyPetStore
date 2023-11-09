package org.csu.mypetstore.service.validator;

import org.csu.mypetstore.repository.RedisCache;
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
    public static Map<String,String> CheckRegisterParams(Map<String,String> params){
        HashMap<String,String> errorinfo=new HashMap<>();
        for(Map.Entry<String,String> entry:params.entrySet()){
            String key=entry.getKey();
            String value=entry.getValue();
            if(value==null||value.equals("")){
                errorinfo.put(key,"不能为空");
            }
        }
        if(!FormatUtil.checkEmail(params.get("email"))){
            errorinfo.put("email","邮箱格式不正确");
        }
        if(!FormatUtil.checkPhone(params.get("phone"))){
            errorinfo.put("phone","手机号格式不正确");
        }
        return errorinfo;
    }
}
