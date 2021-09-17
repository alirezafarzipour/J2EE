package com.J2EE.service;

import com.J2EE.model.Process;

import java.io.File;
import java.rmi.Remote;
import java.util.List;

public interface ProcessService extends Remote {
    void startProcessByKey(String pname) throws Exception;
    void signalExecution(String pid) throws Exception;
    void createDeploymentFromFile(File file) throws Exception;
    List<Process> findRoleProcess(String role) throws Exception;
    List<Process> findAllProcess() throws Exception;
}
