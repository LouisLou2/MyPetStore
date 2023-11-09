package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.repository.AccountDAO;
import org.csu.mypetstore.repository.Impl.AccountDAOImpl;
import org.csu.mypetstore.repository.RedisCache;

import java.util.Map;

public class AccountService {

    private static AccountDAO accountDAO;
    
    static{
        accountDAO = new AccountDAOImpl();
    }

    public static Account getAccount(String username) {
        Map<String,String>map= RedisCache.getAccountModelById(username);
        return accountDAO.getAccountByUsername(username);
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
}
