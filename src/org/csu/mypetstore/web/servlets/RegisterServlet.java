package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.ErrorEnum;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.validator.AccountValidator;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String NEWACCOUNTFORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String SIGN_ON="/account/signin";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        HttpSession session = request.getSession();
        String value1=request.getParameter("vCode");
        boolean isSame=AccountValidator.checkImageCode(value1, session.getId());
        if(!isSame){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", ErrorEnum.VerificationError.IMAGECODE.getMessage());
            response.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
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
        Account account1 = new Account(username, password, firstName, lastName, email, phone, address1, address2, city, state, zip, country, languagePreference, favouriteCategoryId, Boolean.parseBoolean(listOption), Boolean.parseBoolean(bannerOption));
        
        AccountService.insertAccount(account1);
        session.setAttribute("account", account1);
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.setLocation(SIGN_ON);
        response.getWriter().write(restResponse.ToJsonStr());
        response.getWriter().close();
    }
}
