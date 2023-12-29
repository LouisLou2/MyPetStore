package org.csu.mypetstore.web.servlets.restful;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;
import org.csu.mypetstore.domain.RestResponse;
import org.csu.mypetstore.service.CatalogService;

import java.io.IOException;

public class SimpleItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ResultCodeEnum.SUCCESS);
        try{
            String itemId = req.getParameter("itemId");
            restResponse.insertLoading("item", CatalogService.getSimpleItem(itemId));
        }catch (Exception e){
            restResponse.setCode(ResultCodeEnum.FAIL);
            restResponse.insertLoading("error",e.getMessage());
        }
        resp.getWriter().write(restResponse.ToJsonStr());
        resp.getWriter().close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
