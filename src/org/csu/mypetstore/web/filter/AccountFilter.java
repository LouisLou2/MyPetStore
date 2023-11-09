package org.csu.mypetstore.web.filter;

import jakarta.servlet.*;
import org.csu.mypetstore.constant.UserActionMap;
import org.csu.mypetstore.domain.NormalLog;
import org.csu.mypetstore.service.LogService;
import org.csu.mypetstore.utils.URLHelper;

import java.io.IOException;

public class AccountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        String mark = "account";
        //转为HttpRequset
        jakarta.servlet.http.HttpServletRequest req = (jakarta.servlet.http.HttpServletRequest) request;
        //time自动获取
        NormalLog normalLog = new NormalLog();
        normalLog.setIp(req.getRemoteAddr());
        normalLog.setUsername((String) req.getSession().getAttribute("username"));
        String point= URLHelper.getLayerFromURI(3,req.getRequestURI());
        normalLog.setAction(UserActionMap.getActionCode(point));
        LogService.InsertNomalLog(normalLog);
        //放行
        chain.doFilter(request, response);
    }
}
