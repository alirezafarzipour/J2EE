package com.J2EE.server;

import com.J2EE.common.JBPM;
import com.J2EE.service.ProcessServiceImp;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class RMI {
    public static void main(String[] args) throws Exception {
        try{
            LocateRegistry.createRegistry(1099);
            new JBPM();
            ProcessServiceImp processServiceImp = new ProcessServiceImp();
            Naming.rebind("JBPM",processServiceImp);
            System.out.println("Server Started...");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("[Failed to start]");
        }

    }
}
