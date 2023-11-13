package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.repository.RedisCache;
import org.csu.mypetstore.service.VerifiyService;
import org.csu.mypetstore.utils.VerifyUtil;

import java.io.IOException;

public class EmailCodeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        try{
            String email= req.getParameter("email");
            if(email==null)return;
            String numcode= VerifyUtil.getVerifyCodeNumber();
            RedisCache.setEmailCode(numcode,email);
            VerifiyService.SendEmailVerifyCode(email,numcode);
            restResponse.setCode(ResultCodeEnum.SUCCESS);
        }catch(Exception e){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error", e.getMessage());
        }
        resp.getWriter().write(restResponse.ToJsonStr());
        resp.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
