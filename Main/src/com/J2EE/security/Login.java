package com.J2EE.security;

import com.J2EE.service.LogService;
import org.apache.catalina.realm.GenericPrincipal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@WebServlet("/login.do")
public class Login extends HttpServlet {
    LogService logService = new LogService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String hashMD5 = Cryptography.hashMD5(req.getParameter("password"));
            String username = req.getParameter("username");
            req.login(username, hashMD5);
            GenericPrincipal genericPrincipal = (GenericPrincipal) req.getUserPrincipal();
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("role", genericPrincipal.getRoles()[0]);
            logService.saveLog(username, "Logged in successfully. time: " + new Date());
            resp.sendRedirect("/" + genericPrincipal.getRoles()[0] + "/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            logService.saveLog(req.getParameter("username"), "[LOGIN FAILED] IP: " + req.getRemoteAddr() + "| PORT: " + req.getRemotePort() + " .time: " + new Date());
            resp.sendRedirect("index.jsp");
        }
    }
}
