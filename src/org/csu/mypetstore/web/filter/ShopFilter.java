package org.csu.mypetstore.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.csu.mypetstore.constant.UserActionMap;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.ShopLog;
import org.csu.mypetstore.service.LogService;

import java.io.IOException;

public class ShopFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        String mark = "shop";
        //转为HttpRequset
        jakarta.servlet.http.HttpServletRequest req = (jakarta.servlet.http.HttpServletRequest) request;
        //time自动获取
        ShopLog shopLog = new ShopLog();
        shopLog.setIp(req.getRemoteAddr());
        jakarta.servlet.http.HttpSession session=((HttpServletRequest) request).getSession();
        
        Account account=(Account)session.getAttribute("account");
        String username="guest";
        if(account!=null)username=account.getUsername();
        shopLog.setUsername(username);
        
        String uri= req.getRequestURI();
        String[] uriList=uri.split("/");
        String actionStr=uriList[3];
        String typeStr=uriList[4];
        String param=req.getQueryString();
        
        shopLog.setAction(UserActionMap.getActionCode(actionStr));
        shopLog.setType(UserActionMap.getTypeCode(typeStr));
        shopLog.setInfo(param);
        LogService.InsertShopLog(shopLog);
        //放行
        chain.doFilter(request, response);
    }
}
