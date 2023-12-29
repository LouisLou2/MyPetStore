package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.constant.enums.MarkerEnum;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.repository.RedisCache;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.utils.URLHelper;

import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String MAIN_SERVLET = "/main";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        RestResponse restResponse = new RestResponse();
        HttpSession session = request.getSession();
        String errorInfo=null;
        String signinWay = request.getParameter("signinWay");
        boolean active = (request.getParameter("active"))!=null;
        Account account = null;
        if(signinWay.equals("usePassword")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String imgCode = request.getParameter("vCode");
            String sessionId = request.getSession().getId();
            errorInfo=AccountService.checkUserInfo(username,password,imgCode,sessionId);
            if(errorInfo==null){
                account = AccountService.getAccount(username);
            }
        }else if(signinWay.equals("useEmail")){
            String email = request.getParameter("email");
            String emailCode = request.getParameter("emailCode");
            errorInfo=AccountService.checkUserInfo(email,emailCode);
            if(errorInfo==null){
                account = AccountService.getAccountByEmail(email);
            }
        }else{
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error", "unknown signin way");
            response.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        if(errorInfo!=null){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error", errorInfo);
            response.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        session.setAttribute("account", account);
        RedisCache.setModelById(account.getUsername(), MarkerEnum.USER_ID, account);
        
        String originalLink=(String)session.getAttribute("originalLink");
        session.removeAttribute("originalLink");
        if(!active){
            if(originalLink==null) {
                restResponse.setLocation(URLHelper.getFullURL(MAIN_SERVLET));
            }
            else{
                restResponse.setLocation(URLHelper.getFullURL(originalLink));
            }
        }
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        response.getWriter().write(restResponse.ToJsonStr());
        response.getWriter().close();
    }
}