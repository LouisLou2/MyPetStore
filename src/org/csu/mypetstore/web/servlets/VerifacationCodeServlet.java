package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.repository.RedisCache;
import org.csu.mypetstore.utils.VerifyUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class VerifacationCodeServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        HttpSession session=req.getSession();
        //设置浏览器不要缓存此图片
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        //getCode
        String code= VerifyUtil.getVerifyCode();
        //得到bufferedImage对象
        BufferedImage image=VerifyUtil.getCodeImage(code);
        //将验证码存入redis
        RedisCache.setImageCode(code, session.getId());
        //将图像传到客户端
        ServletOutputStream sos=resp.getOutputStream();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        byte[] buffer=baos.toByteArray();
        resp.setContentLength(buffer.length);
        sos.write(buffer);
        baos.close();
        sos.close();
    }
}
