package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.AccountService;

import java.io.IOException;

public class UpdateAccountServlet extends HttpServlet {

    private static final String EDITACOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    private Account account;
    private AccountService accountService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        String operation = request.getParameter("operation");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String username = account.getUsername();
        if(operation.equals("PasswordModify")){
            String password=request.getParameter("password");
            String newPassword=request.getParameter("newPassword");
            String error=AccountService.updatePassword(username,password,newPassword);
            if(error==null) {
                account.setPassword(newPassword);
                session.setAttribute("account", account);
                restResponse.setCode(ResultCodeEnum.SUCCESS);
            }
            else{
                restResponse.setCode(ResultCodeEnum.FAIL);
                restResponse.insertLoading("error",error);
            }
        }else{
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");
            String country = request.getParameter("country");
            String languagePreference = request.getParameter("languagePreference");
            String favouriteCategoryId = request.getParameter("favouriteCategoryId");
            String listOption = request.getParameter("listOption");
            String bannerOption = request.getParameter("bannerOption");
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setAddress1(address1);
            account.setAddress2(address2);
            account.setCity(city);
            account.setState(state);
            account.setZip(zip);
            account.setCountry(country);
            account.setLanguagePreference(languagePreference);
            account.setFavouriteCategoryId(favouriteCategoryId);
            account.setListOption(Boolean.parseBoolean(listOption));
            account.setBannerOption(Boolean.parseBoolean(bannerOption));
            AccountService.updateAccount(account);
            session.setAttribute("account", account);
            restResponse.setCode(ResultCodeEnum.SUCCESS);
        }
        response.getWriter().write(restResponse.ToJsonStr());
        response.getWriter().close();
    }
}
