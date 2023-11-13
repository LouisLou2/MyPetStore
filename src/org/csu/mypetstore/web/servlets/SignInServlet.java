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
        String fullOriginalLink=null;
        //注意，登录携带的参数是originalLink，是相对于APP_ROOT的路径。现在将他转换为绝对路径
        String originalLinkInRequest = request.getParameter("originalLink");
        if(!originalLinkInRequest.equals("null")){
            fullOriginalLink= URLHelper.getLocationWithRoot(originalLinkInRequest);
            request.removeAttribute("originalLink");
            session.removeAttribute("originalLink");
        }else{
            String originalLinkInSession = (String) session.getAttribute("originalLink");
            if(originalLinkInSession!=null){
                fullOriginalLink= URLHelper.getLocationWithRoot(originalLinkInSession);
                session.removeAttribute("originalLink");
            }
        }
        if(fullOriginalLink!=null) {
            restResponse.setLocation(fullOriginalLink);
        }else{
            restResponse.setLocation(AppProperties.APP_ROOT+MAIN_SERVLET);
        }
        response.getWriter().write(restResponse.ToJsonStr());
        response.getWriter().close();
    }
}
