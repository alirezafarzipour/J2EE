package com.J2EE.common;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;

import java.io.*;
import java.net.URL;

/**
 * @version 2.1
 * @author Alireza farzipour
 **/


public class JBPM {
    private static ProcessEngine processEngine = new Configuration().setResource("my.jbpm.cfg.xml").buildProcessEngine();

    public static ExecutionService getExecutionService() {
        return processEngine.getExecutionService();
    }

    static {
        processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("test.jpdl.xml").deploy();
        System.out.println("Process engine config completed.");
    }

    public static void reDeployFromUrl(URL url) {
        processEngine.getRepositoryService().createDeployment().addResourceFromUrl(url).deploy();
        System.out.println("New process added from url");
    }

    public static void reDeployFromInputStream(String name, InputStream inputStream) {
        processEngine.getRepositoryService().createDeployment().addResourceFromInputStream(name, inputStream).deploy();
        System.out.println("New process added from inputStream");
    }

    public static void reDeployFromFile(File file) {
        processEngine.getRepositoryService().createDeployment().addResourceFromFile(file).deploy();
        System.out.println("New process added from file");
    }
}
