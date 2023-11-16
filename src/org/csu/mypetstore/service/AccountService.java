package org.csu.mypetstore.service;

import org.csu.mypetstore.constant.enums.ErrorEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.repository.AccountDAO;
import org.csu.mypetstore.repository.Impl.AccountDAOImpl;
import org.csu.mypetstore.repository.RedisCache;
import org.csu.mypetstore.service.validator.AccountValidator;

import java.util.Map;

public class AccountService {

    private static AccountDAO accountDAO;
    
    static{
        accountDAO = new AccountDAOImpl();
    }
    //return的是错误信息
    public static String checkUserInfo(String username,String password,String imgCode, String sessionId){
        boolean isSame= AccountValidator.checkImageCode(imgCode, sessionId);
        if(!isSame){
            return ErrorEnum.VerificationError.IMAGECODE.getMessage();
        }
        Account account = AccountService.getAccount(username, password);
        if(account == null){
            return ErrorEnum.VerificationError.NOTMATCH.getMessage();
        }
        return null;
    }
    //return的是错误信息
    public static String checkUserInfo(String email,String emailCode){
        //检查邮箱是否存在
        if(!AccountService.isEmailExist(email)){
            return ErrorEnum.VerificationError.NOTEXIST.getMessage();
        }
        String code = RedisCache.getEmailCode(email);
        if(code == null||!code.equals(emailCode)){
            return ErrorEnum.VerificationError.EMAILCODE.getMessage();
        }
        return null;
    }
    public static Account getAccount(String username) {
        Map<String,String>map= RedisCache.getAccountModelById(username);
        return accountDAO.getAccountByUsername(username);
    }
    public static Account getAccountByEmail(String email) {
        return accountDAO.getAccountByEmail(email);
    }
    public static boolean isFieldExists(String key, String value){
        boolean isexist=false;
        switch (key){
            case "username":
                isexist=isExist(value);
                break;
            case "email":
                isexist=isEmailExist(value);
                break;
            case "phone":
                isexist=isPhoneExist(value);
                break;
        }
        return isexist;
    }
    public static boolean isExist(String username){
        return accountDAO.isExist(username);
    }
    public static Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDAO.getAccountByUsernameAndPassword(account);
    }
    public static boolean isEmailExist(String email){
        return accountDAO.isEmailExist(email);
    }
    public static boolean isPhoneExist(String phone){
        return accountDAO.isPhoneExist(phone);
    }
    public static void insertAccount(Account account) {
        accountDAO.insertAccount(account);
        accountDAO.insertProfile(account);
        accountDAO.insertSignon(account);
    }

    public static void updateAccount(Account account) {
        accountDAO.updateAccount(account);
        accountDAO.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDAO.updateSignon(account);
        }
    }
    public static String updatePassword(String username,String password,String newPassword){
        //检查旧密码是否正确
        String oldPassword=accountDAO.getPasswordByUsername(username);
        if(!oldPassword.equals(password)){
            return "旧密码错误";
        }
        //检查新密码是否符合规范
        if(newPassword.length()<6){
            return "新密码长度不能小于6位";
        }
        //更新密码
        accountDAO.updatePasswordByUsername(username,newPassword);
        return null;
    }
}
