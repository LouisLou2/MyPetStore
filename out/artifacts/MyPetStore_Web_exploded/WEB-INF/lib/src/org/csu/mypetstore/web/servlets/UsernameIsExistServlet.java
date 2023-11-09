package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            Account account = new Account();
            account.setUsername(username);
            AccountService accountService = new AccountService();

            response.setContentType("text/xml");
            PrintWriter out = response.getWriter();

            if(accountService.isExist(username)){
                out.println("<msg>Exist</msg>");
            }
            else {
                out.println("<msg>NotExist</msg>");
            }
            out.flush();
            out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
