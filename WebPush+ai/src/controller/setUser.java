package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setUser.do")
public class setUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("channel",req.getParameter("channel"));
        req.getSession().setAttribute("username",req.getParameter("username"));
        resp.sendRedirect("/one.jsp");
    }
}
