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

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VerificationServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String NEWACCOUNTFORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String SIGN_ON_FROM_SERVLET= "/page/signin";
    //此函数用于验证各参数是否符合要求，（包括验证码），如果注册成功，跳转到登录界面，如果失败，仍在注册界面
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedWriter writer = new BufferedWriter(resp.getWriter());
        RestResponse restResponse = new RestResponse();
        HttpSession session = req.getSession();
        String value = req.getParameter("password");
        String value1 = req.getParameter("vCode");
        boolean isSame = AccountValidator.checkImageCode(value1, session.getId());
        if (!isSame) {
            restResponse = new RestResponse();
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", ErrorEnum.VerificationError.IMAGECODE.getMessage());
            writer.write(restResponse.ToJsonStr());
            writer.close();
            return;
        }
        //获取参数放入Map中
        ConcurrentHashMap<String, String> params = new ConcurrentHashMap<>();
        params.put("username", req.getParameter("username"));
        params.put("password", req.getParameter("password"));
        params.put("firstName", req.getParameter("firstName"));
        params.put("lastName", req.getParameter("lastName"));
        params.put("email", req.getParameter("email"));
        params.put("phone", req.getParameter("phone"));
        params.put("address1", req.getParameter("address1"));
        params.put("address2", req.getParameter("address2"));
        params.put("city", req.getParameter("city"));
        params.put("state", req.getParameter("state"));
        params.put("zip", req.getParameter("zip"));
        params.put("country", req.getParameter("country"));
        params.put("languagePreference", req.getParameter("languagePreference"));
        params.put("favouriteCategoryId", req.getParameter("favouriteCategoryId"));
        params.put("listOption", req.getParameter("listOption"));
        params.put("bannerOption", req.getParameter("bannerOption"));
        //检查参数是否符合要求
        Map<String, String> errorinfo = AccountValidator.CheckRegisterParams(params);
        if (errorinfo.size() > 0) {
            restResponse = new RestResponse();
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", errorinfo);
            writer.write(restResponse.ToJsonStr());
            writer.close();
            return;
        }
        Account account1 = new Account();
        account1.setUsername(params.get("username"));
        account1.setPassword(params.get("password"));
        account1.setFirstName(params.get("firstName"));
        account1.setLastName(params.get("lastName"));
        account1.setEmail(params.get("email"));
        account1.setPhone(params.get("phone"));
        account1.setAddress1(params.get("address1"));
        account1.setAddress2(params.get("address2"));
        account1.setCity(params.get("city"));
        account1.setState(params.get("state"));
        account1.setZip(params.get("zip"));
        account1.setCountry(params.get("country"));
        account1.setLanguagePreference(params.get("languagePreference"));
        account1.setFavouriteCategoryId(params.get("favouriteCategoryId"));
        account1.setListOption(Boolean.parseBoolean(params.get("listOption")));
        account1.setBannerOption(Boolean.parseBoolean(params.get("bannerOption")));

        AccountService.insertAccount(account1);
        restResponse = new RestResponse();
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        restResponse.setLocation(SIGN_ON_FROM_SERVLET);
        writer.write(restResponse.ToJsonStr());
        writer.close();
    }
}
