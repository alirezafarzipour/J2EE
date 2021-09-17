package controller;

import service.ChatService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dateShower.do")
public class DateShow extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (Object j :
                ChatService.getInstance().selectAll()) {
            resp.getWriter().print("<h3>"+j+"</h3>");
        }
    }
}
