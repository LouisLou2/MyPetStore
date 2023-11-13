package org.csu.mypetstore.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.csu.mypetstore.constant.AppProperties;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.ErrorManager;
import org.csu.mypetstore.service.ResourceManager;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final String SignInPage="/page/account/signin";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url=((HttpServletRequest)servletRequest).getRequestURI();
        String queryStr=((HttpServletRequest)servletRequest).getQueryString();
        if(ResourceManager.isStaticResource(url)){
            //静态资源，直接放行,虽然静态资源也不代表就一定是公开资源，但是这里暂时不做处理
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(ResourceManager.isTraditionalServlet(url)){
            //传统servlet，需要验证用户是否登录
            TraditionalFilter(servletRequest,servletResponse,filterChain);
            return;
        }
        if(ResourceManager.isRestfulServlet(url)){
            //restful接口，需要验证用户是否登录
            RestfulFilter(servletRequest,servletResponse,filterChain);
            return;
        }
    }
    private void TraditionalFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
        //验证用户是否登录
        //转为HttpRequset
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //获取session中是否有account
        Account account = (Account) req.getSession().getAttribute("account");
        String queryStr = req.getQueryString();
        //如果没有account，说明没有登录，跳转到登录页面
        if (account == null) {
            //记下来用户想要访问的页面
            String requestURI = req.getRequestURI();
            String targetURL = (queryStr == null) ? requestURI : (requestURI + "?" + queryStr);
            //将用户想要访问的页面保存到session中
            req.getSession().setAttribute("originalLink", targetURL);
            req.getRequestDispatcher(SignInPage).forward(servletRequest,servletResponse);
            return;
        }
        //如果有account，说明已经登录，检测是否有权限访问
        Object username=servletRequest.getAttribute("username");
        if(username==null){
            //说明此次访问与用户无关，直接放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //说明此次访问与用户有关，检测是否有权限访问
        if(!username.equals(account.getUsername())){
            //说明没有权限访问
            //得到errorPage
            String errorPage = ErrorManager.getErrorPage();
            //转发到errorPage
            req.getRequestDispatcher(errorPage).forward(servletRequest,servletResponse);
            return;
        }
        //说明有权限访问
        filterChain.doFilter(servletRequest,servletResponse);
    }
    private void RestfulFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
        //验证用户是否登录
        //转为HttpRequset
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //获取session中是否有account
        Account account = (Account) req.getSession().getAttribute("account");
        String queryStr = req.getQueryString();
        //如果没有account，说明没有登录，将登录页面的地址返回给前端
        if (account == null) {
            //记下来用户想要访问的页面
            String requestURI = req.getRequestURI();
            String targetURL = (queryStr == null) ? requestURI : (requestURI + "?" + queryStr);
            //将用户想要访问的页面保存到session中
            req.getSession().setAttribute("originalLink", targetURL);
            RestResponse restResponse=new RestResponse();
            String SignInFullUrl=AppProperties.APP_ROOT+SignInPage;
            restResponse.setCode(ResultCodeEnum.UNAUTHORIZED);
            restResponse.setLocation(SignInFullUrl);
            restResponse.insertLoading("error","请先登录");
            servletResponse.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        //如果有account，说明已经登录，检测是否有权限访问
        Object username=servletRequest.getAttribute("username");
        if(username==null){
            //说明此次访问与用户无关，直接放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //说明此次访问与用户有关，检测是否有权限访问
        if(!username.equals(account.getUsername())){
            //说明没有权限访问
            RestResponse restResponse=new RestResponse();
            restResponse.setCode(ResultCodeEnum.ERROR);
            restResponse.insertLoading("error","没有权限访问");
            servletResponse.getWriter().write(restResponse.ToJsonStr());
            return;
        }
        //说明有权限访问
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
