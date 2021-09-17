package com.J2EE.service;

import com.J2EE.DA.ProcessDA;
import com.J2EE.common.JBPM;
import com.J2EE.model.Process;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ProcessServiceImp extends UnicastRemoteObject implements ProcessService {


    public ProcessServiceImp() throws RemoteException { }

    @Override
    public void startProcessByKey(String pname) {
        JBPM.getExecutionService().startProcessInstanceByKey(pname).getId();
    }

    @Override
    public void createDeploymentFromFile(File fileName) {
        JBPM.reDeployFromFile(fileName);
    }

    @Override
    public void signalExecution(String pid) {
        JBPM.getExecutionService().signalExecutionById(pid);
    }

    @Override
    public List<Process> findRoleProcess(String role) {
        List<Process> processList = new ArrayList<>();
        try {
            ProcessDA processDA = new ProcessDA();
            processList = processDA.selectByRole(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processList;
    }

    @Override
    public List<Process> findAllProcess() {
        List<Process> processList = new ArrayList<>();
        try {
            ProcessDA processDA = new ProcessDA();
            processList = processDA.selectAllProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processList;
    }

}
