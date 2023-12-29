package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.repository.AccountDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {

    private static final String getAccountByUsernameString = "SELECT" +
            "          SIGNON.USERNAME," +
            "          ACCOUNT.EMAIL," +
            "          ACCOUNT.FIRSTNAME," +
            "          ACCOUNT.LASTNAME," +
            "          ACCOUNT.STATUS," +
            "          ACCOUNT.ADDR1 AS address1," +
            "          ACCOUNT.ADDR2 AS address2," +
            "          ACCOUNT.CITY," +
            "          ACCOUNT.STATE," +
            "          ACCOUNT.ZIP," +
            "          ACCOUNT.COUNTRY," +
            "          ACCOUNT.PHONE," +
            "          PROFILE.LANGPREF AS languagePreference," +
            "          PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "          PROFILE.MYLISTOPT AS listOption," +
            "          PROFILE.BANNEROPT AS bannerOption," +
            "          BANNERDATA.BANNERNAME" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA" +
            "    WHERE ACCOUNT.USERID = ?" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID" +
            "      AND PROFILE.USERID = ACCOUNT.USERID" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String getAccountByEmailString = "SELECT" +
            "          SIGNON.USERNAME," +
            "          ACCOUNT.EMAIL," +
            "          ACCOUNT.FIRSTNAME," +
            "          ACCOUNT.LASTNAME," +
            "          ACCOUNT.STATUS," +
            "          ACCOUNT.ADDR1 AS address1," +
            "          ACCOUNT.ADDR2 AS address2," +
            "          ACCOUNT.CITY," +
            "          ACCOUNT.STATE," +
            "          ACCOUNT.ZIP," +
            "          ACCOUNT.COUNTRY," +
            "          ACCOUNT.PHONE," +
            "          PROFILE.LANGPREF AS languagePreference," +
            "          PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "          PROFILE.MYLISTOPT AS listOption," +
            "          PROFILE.BANNEROPT AS bannerOption," +
            "          BANNERDATA.BANNERNAME" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA" +
            "    WHERE ACCOUNT.EMAIL= ?" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID" +
            "      AND PROFILE.USERID = ACCOUNT.USERID" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String getAccountByUsernameAndPasswordString = "SELECT \n" +
            "SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, \n" +
            "ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY,  ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, \n" +
            "PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, \n" +
            "BANNERDATA.BANNERNAME \n" +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA \n" +
            "WHERE ACCOUNT.USERID = ?\n" +
            "AND SIGNON.PASSWORD = ?\n" +
            "AND SIGNON.USERNAME = ACCOUNT.USERID \n" +
            "AND PROFILE.USERID = ACCOUNT.USERID \n" +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String insertAccountString = "    INSERT INTO ACCOUNT" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
    private static final String insertProfileString = "   INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";
    private static final String insertSignonString = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";
    private static final String updateAccountString = "UPDATE ACCOUNT SET" +
            "      EMAIL = ?," +
            "      FIRSTNAME = ?," +
            "      LASTNAME = ?," +
            "      STATUS = ?," +
            "      ADDR1 = ?," +
            "      ADDR2 = ?," +
            "      CITY = ?," +
            "      STATE = ?," +
            "      ZIP = ?," +
            "      COUNTRY = ?," +
            "      PHONE = ?" +
            "    WHERE USERID = ?";
    private static final String updateProfileString = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";
    private static final String updateSignonString = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
    private static final String getPasswordByUsernameString = "select password from signon where username = ?";
    private static final String isExistString = "select ifnull((select 1  from account where userid = ? LIMIT 1 ), 0)";
    private static final String isEmailExistString = "select ifnull((select 1  from account where email = ? LIMIT 1 ), 0)";
    private static final String isPhoneExistString = "select ifnull((select 1  from account where phone = ? LIMIT 1 ), 0)";
    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(getAccountByUsernameString);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return account;
    }

    @Override
    public Account getAccountByEmail(String email) {
        Account account = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(getAccountByEmailString);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account1 = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(getAccountByUsernameAndPasswordString);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account1 = new Account();
                account1.setUsername(resultSet.getString(1));
                account1.setEmail(resultSet.getString(2));
                account1.setFirstName(resultSet.getString(3));
                account1.setLastName(resultSet.getString(4));
                account1.setStatus(resultSet.getString(5));
                account1.setAddress1(resultSet.getString(6));
                account1.setAddress2(resultSet.getString(7));
                account1.setCity(resultSet.getString(8));
                account1.setState(resultSet.getString(9));
                account1.setZip(resultSet.getString(10));
                account1.setCountry(resultSet.getString(11));
                account1.setPhone(resultSet.getString(12));
                account1.setLanguagePreference(resultSet.getString(13));
                account1.setFavouriteCategoryId(resultSet.getString(14));
                account1.setListOption(resultSet.getBoolean(15));
                account1.setBannerOption(resultSet.getBoolean(16));
                account1.setBannerName(resultSet.getString(17));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return account1;
    }

    @Override
    public boolean isExist(String username) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(isExistString);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return true;
    }
    @Override
    public boolean isEmailExist(String email) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(isEmailExistString);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return true;
    }

    @Override
    public boolean isPhoneExist(String phone) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(isPhoneExistString);
            preparedStatement.setString(1, phone);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        return true;
    }

    @Override
    public void insertAccount(Account account) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(insertAccountString);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertProfile(Account account) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(insertProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertSignon(Account account) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(insertSignonString);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateAccount(Account account) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(updateAccountString);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateProfile(Account account) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(updateProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateSignon(Account account) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(updateSignonString);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public String getPasswordByUsername(String username) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String password = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(getPasswordByUsernameString);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                password= resultSet.getString(1);
            }
        } catch (Exception e) {
            password = null;
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
            DBUtil.closeResultSet(resultSet);
        }
        return password;
    }

    @Override
    public void updatePasswordByUsername(String username, String password) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(updateSignonString);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }
}
