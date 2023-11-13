package org.csu.mypetstore.service;
//用来选择展示错误信息的页面
public class ErrorManager {
    private static final String ERROR_PAGE = "/WEB-INF/jsp/common/Error.jsp";
    public static String getErrorPage(){
        return ERROR_PAGE;
    }
}
