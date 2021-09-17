package com.J2EE.service;

import com.J2EE.repository.LogDA;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class LogService {

    public void saveLog(String username, String log) {
        LogDA logDA = new LogDA();
        logDA.insert(username, log);
    }

    public List<String> findAllLog() {
        LogDA logDA = new LogDA();
        return logDA.selectAll();
    }

    public List<String> findLogByUsername(String username){
        LogDA logDA = new LogDA();
        return logDA.selectByUsername(username);
    }

}
