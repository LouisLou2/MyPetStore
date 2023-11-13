package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Account;

public interface AccountDAO {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);
    boolean isExist(String username);
    boolean isEmailExist(String email);
    boolean isPhoneExist(String phone);
    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);
}
