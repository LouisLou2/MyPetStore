package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.AppProperties;
import org.csu.mypetstore.constant.enums.ErrorEnum;
import org.csu.mypetstore.constant.enums.MarkerEnum;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.repository.RedisCache;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.validator.AccountValidator;

import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String MAIN_SERVLET = AppProperties.APP_ROOT+"/main";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RestResponse restResponse = new RestResponse();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String imgCode = request.getParameter("vCode");
        
        boolean isSame=AccountValidator.checkImageCode(imgCode, request.getSession().getId());
        if(!isSame){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", ErrorEnum.VerificationError.IMAGECODE.getMessage());
            response.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        Account account = AccountService.getAccount(username, password);
        if(account == null){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.setLocation("");
            restResponse.insertLoading("error", ErrorEnum.VerificationError.NOTMATCH.getMessage());
            response.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        RedisCache.setModelById(account.getUsername(), MarkerEnum.USER_ID, account);
        
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        String theLocation=(session.getAttribute("originalLink")==null)?MAIN_SERVLET:(String) session.getAttribute("originalLink");
        restResponse.setLocation(theLocation);
        response.getWriter().write(restResponse.ToJsonStr());
        response.getWriter().close();
        //if(account != null){
        //    HttpServletRequest httpRequest= request;
        //    String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
        //            + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
        //
        //    LogService logService = new LogService();
        //    String logInfo = logService.logInfo(" ") + strBackUrl + " 用户登录";
        //    logService.insertLogInfo(account.getUsername(), logInfo);
        //}
    }
}
