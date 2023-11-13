package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.AccountService;

import java.io.IOException;
import java.io.PrintWriter;

public class CheckExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        response.setContentType("application/json;charset=utf-8");
        String key = request.getParameter("key");
        String value=request.getParameter("value");
        if(key==null||value==null){
            restResponse.setCode(ResultCodeEnum.FAIL);
            PrintWriter out = response.getWriter();
            out.write(restResponse.ToJsonStr());
            out.flush();
            out.close();
        }
        if(AccountService.isFieldExists(key,value)){
            restResponse.setCode(ResultCodeEnum.FAIL);
        }else{
            restResponse.setCode(ResultCodeEnum.SUCCESS);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(restResponse.ToJsonStr());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
