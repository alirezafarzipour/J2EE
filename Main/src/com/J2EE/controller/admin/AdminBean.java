package com.J2EE.controller.admin;

import com.J2EE.model.Admin;
import com.J2EE.model.Person;
import com.J2EE.model.User;
import com.J2EE.security.Cryptography;
import com.J2EE.service.CRUDService;
import com.J2EE.service.LogService;
import com.J2EE.service.ProcessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;


//@RolesAllowed("admin")
@Path("/adminAct")
@Named
@RequestScoped
public class AdminBean {
    @EJB
    private CRUDService crudService;
    @EJB
    private LogService logService;


    @Path("/findAllUser.do")
    public void findAllUser(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call findAllUser()" + ". time: " + new Date());

            req.setAttribute("personList", crudService.findAllUser());
            req.getRequestDispatcher("/admin/userManager.jsp").forward(req, res);
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call findAllUser()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/addUser.do")
    public void addUser(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call addUser() for: " + req.getParameter("username") + " . time: " + new Date());
            String hashMD5 = Cryptography.hashMD5(req.getParameter("password"));
            Person person = new Person(req.getParameter("name"),
                    req.getParameter("family"),
                    req.getParameter("username"));
            User user = new User(req.getParameter("username"),
                    "person", hashMD5);
            crudService.addUser(person, user);
            res.sendRedirect("/adminAct/findAllUser.do");
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call addUser()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/updateUser.do")
    public void updateUser(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call updateUser() for: " + req.getParameter("username") + " . time: " + new Date());

            Person person = new Person(
                    Long.parseLong(req.getParameter("id")),
                    req.getParameter("name"),
                    req.getParameter("family"),
                    req.getParameter("username"));
            crudService.updateUser(person);
            res.sendRedirect("/adminAct/findAllUser.do");
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call updateUser()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/deleteUser.do")
    public void deleteUser(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call deleteUser() for: " + req.getParameter("username") + " . time: " + new Date());

            Person person = new Person();
            person.setId(Long.parseLong(req.getParameter("id")));
            crudService.deleteUser(person);
            res.sendRedirect("/adminAct/findAllUser.do");
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call deleteUser()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/findAllAdmin.do")
    public void findAllAdmin(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call findAllAdmin() . time: " + new Date());

            req.setAttribute("adminList", crudService.findAllAdmin());
            req.getRequestDispatcher("/admin/adminManager.jsp").forward(req, res);
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call findAllAdmin()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/addAdmin.do")
    public void addAdmin(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call addAdmin() for: " + req.getParameter("username") + " . time: " + new Date());

            String hashMD5 = Cryptography.hashMD5(req.getParameter("password"));
            Admin admin = new Admin(req.getParameter("name"),
                    req.getParameter("family"),
                    req.getParameter("username"));
            User user = new User(req.getParameter("username"),
                    "admin", hashMD5);
            crudService.addAdmin(admin, user);
            res.sendRedirect("/adminAct/findAllAdmin.do");
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call addAdmin()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/showAllLog.do")
    public void showAllLog(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call showAllLog() . time: " + new Date());

            req.setAttribute("logList", logService.findAllLog());
            req.getRequestDispatcher("/admin/log.jsp").forward(req, res);
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call showAllLog()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/showLogByUsername.do")
    public void showLogByUsername(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call showLogByUsername() for: " + req.getParameter("username") + ". time: " + new Date());

            req.setAttribute("logListByUsername", logService.findLogByUsername(req.getParameter("username")));
            req.getRequestDispatcher("/admin/log.jsp").forward(req, res);
        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call showLogByUsername()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/showAllProcessName.do") //complete
    public void showAllProcessName(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
            req.setAttribute("processNames", processService.findAllProcess());
            req.getRequestDispatcher("/admin/process/startProcess.jsp").forward(req, res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Path("/showAllProcess.do") //complete
    public void showAllProcess(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call showAllProcess()" + ". time: " + new Date());

            ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
            req.setAttribute("processListByRole", processService.findRoleProcess("admin"));
            req.getRequestDispatcher("/admin/process/processBox.jsp").forward(req, res);

        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call showAllProcess()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/executionProcess.do") //complete
    public void executionProcess(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "call executionProcess(), Pid: " + req.getParameter("pid") + ". time: " + new Date());

            ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
            processService.startProcessByKey(req.getParameter("pname"));

            res.sendRedirect("/adminAct/showAllProcess.do");

        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call executionProcess()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
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
            res.sendRedirect("/adminAct/showAllProcess.do");

        } catch (Exception e) {
            logService.saveLog(req.getSession().getAttribute("username").toString(),
                    "[ERROR] call sendSignal()" + ". time: " + new Date() + ". error Message: [" + e.getMessage() + "]");
            e.printStackTrace();
        }
    }

    @Path("/setAdminChannel.do")
    public void setAdminChannel(@Context HttpServletRequest req, @Context HttpServletResponse res){
        try {
            Object username = req.getSession().getAttribute("username");
            Person person = new Person();
            person.setId(Long.parseLong(req.getParameter("id")));
            person = crudService.findUserById(person);
            String Channel = person.getId() + person.getUsername();
            res.sendRedirect("http://localhost/setUser.do?channel=" + Channel + "&username=" + username);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//new process is in Servlet unit

//    public static void main(String[] args) throws Exception {
//        ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
//        System.out.println(processService.Execution("startSale2"));
////            processService.sendSignal("startSale2.240007");
////        List<Process> processList = processService.findRoleProcess("admin");
////        for (Process process :
////                processList) {
////            System.out.println(process.getPid());
////        }
//    }
}
