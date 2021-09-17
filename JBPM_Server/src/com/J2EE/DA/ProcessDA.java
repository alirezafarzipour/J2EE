package com.J2EE.DA;

import com.J2EE.common.DBCP;
import com.J2EE.model.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessDA {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProcessDA() throws Exception {
        connection = DBCP.getConnection();
//        connection.setAutoCommit(false);
    }

    public List<Process> selectByRole(String role) throws SQLException {
        preparedStatement = connection.prepareStatement("select EXECUTION_ from JBPM4_HIST_ACTINST where ACTIVITY_NAME_=? and END_ IS NULL ");
        preparedStatement.setString(1, role);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Process> processList = new ArrayList<>();
        while (resultSet.next()) {
            Process process = new Process().setPid(resultSet.getString("EXECUTION_")).setUname(role);
            processList.add(process);
        }
        preparedStatement.close();
        connection.close();
        return processList;
    }

    public List<Process> selectAllProcess() throws SQLException {
        preparedStatement = connection.prepareStatement("select distinct OBJNAME_ from JBPM4_DEPLOYPROP");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Process> processList = new ArrayList<>();
        while (resultSet.next()) {
            Process process = new Process().setPname(resultSet.getString("OBJNAME_"));
            processList.add(process);
        }
        preparedStatement.close();
        connection.close();
        return processList;
    }

}
