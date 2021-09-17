package com.J2EE.controller.person;

import com.J2EE.model.Person;
import com.J2EE.service.CRUDService;
import com.J2EE.service.LogService;
import com.J2EE.service.ProcessService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;


//@RolesAllowed({"admin","person"})
@Path("/personAct")
@Named
@RequestScoped
public class PersonBean {
    @EJB
    private CRUDService crudService;
    @EJB
    private LogService logService;


    @Path("/showAllProcess.do") //complete
    public void showAllProcess(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call showAllProcess()" + ". time: " + new Date());

            ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
            req.setAttribute("processList", processService.findRoleProcess("person"));
            req.getRequestDispatcher("/person/processBox.jsp").forward(req, res);

        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call showAllProcess()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/sendSignal.do") //complete
    public void sendSignal(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call sendSignal(), Pid: " + req.getParameter("pid") + ". time: " + new Date());

            ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
            processService.signalExecution(req.getParameter("pid"));
            res.sendRedirect("/personAct/showAllProcess.do");

        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call sendSignal()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/setUserChannel.do")
    public void setUserChannel(@Context HttpServletRequest req, @Context HttpServletResponse res){
        try {
            Object username = req.getSession().getAttribute("username");
            Person person = new Person();
            person.setUsername((String) username);
            person = crudService.findUserByUsername(person);
            String Channel = person.getId() + person.getUsername();
            res.sendRedirect("http://localhost/setUser.do?channel=" + Channel + "&username=" + username);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
