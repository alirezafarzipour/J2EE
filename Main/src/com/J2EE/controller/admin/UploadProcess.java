package com.J2EE.controller.admin;

import com.J2EE.service.LogService;
import com.J2EE.service.ProcessService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.Naming;
import java.util.Date;

@WebServlet("/adminAct/UploadProcess")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 3,
        maxRequestSize = 1024 * 1024 * 4)
public class UploadProcess extends HttpServlet {
    LogService logService = new LogService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        logService.saveLog(req.getSession().getAttribute("username").toString(),
                "want to upload new process" + ". time: " + new Date());

        //get the file chosen by the user
        Part filePart = req.getPart("fileToUpload");

        if (filePart.getSubmittedFileName().endsWith(".xml")) {
            InputStream fileInputStream = filePart.getInputStream();
            File processFile = new File(req.getServletContext().getRealPath("")+"/temp.jpdl.xml");
            Files.copy(fileInputStream, processFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            try {
                ProcessService processService = (ProcessService) Naming.lookup("//localhost/JBPM");
                processService.createDeploymentFromFile(processFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.setAttribute("Message", "New process added successfully");
            req.getRequestDispatcher("index.jsp");

            resp.sendRedirect("/adminAct/showAllProcessName.do");
        } else {
            //the file was not a xml
            req.setAttribute("errorMessage", "*Please only upload 'XML' file");
            req.getRequestDispatcher("/admin/process/newProcess.jsp").forward(req, resp);
        }
    }
}
