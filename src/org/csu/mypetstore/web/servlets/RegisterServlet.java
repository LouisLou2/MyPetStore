package org.csu.mypetstore.web.servlets;

import com.alibaba.fastjson2.JSON;
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
import org.csu.mypetstore.utils.BeanFlattener;
import org.csu.mypetstore.utils.URLHelper;

import java.io.IOException;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String NEWACCOUNTFORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String SIGN_ON="/page/account/signin";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        HttpSession session = request.getSession();
        String emailCode=request.getParameter("emailCode");
        String email=request.getParameter("email");
        boolean isSame=AccountValidator.checkEmailCode(emailCode,email);
        if(!isSame){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", ErrorEnum.VerificationError.EMAILCODE.getMessage());
            response.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
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
        Map<String,String> errorInfo=null;
        try {
            errorInfo= AccountValidator.CheckRegisterParams(BeanFlattener.deepToMap(account1));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if(!errorInfo.isEmpty()){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", JSON.toJSONString(errorInfo));
        }else{
            AccountService.insertAccount(account1);
            restResponse.setCode(ResultCodeEnum.SUCCESS);
            String fullURL = URLHelper.getFullURL(SIGN_ON);
            restResponse.setLocation(URLHelper.getFullURL(SIGN_ON));
        }
        response.getWriter().write(restResponse.ToJsonStr());
        response.getWriter().close();
    }
}
