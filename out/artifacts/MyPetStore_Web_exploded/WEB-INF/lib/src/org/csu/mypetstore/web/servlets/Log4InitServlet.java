package org.csu.mypetstore.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Log4InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.setProperty("logPath", "C://Documents and Settings//jl60155//webIVR-workspace//ScriptMaint-WebVersion//logs//myLog.log");
        System.err.println("Log4j Servlet test Path: " + System.getProperty("logPath"));

        //PropertyConfigurator.configure(getServletContext().getRealPath("/") + getInitParameter("configfile"));

    }
}
