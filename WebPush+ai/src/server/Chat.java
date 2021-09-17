package server;

import service.ChatService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 3.0
 * @author Alireza farzipour
 **/


@ServerEndpoint("/chat/{channel}")
public class Chat {

    public Chat() {
        System.out.println("[Chat server started]");
    }

    private static Map<Object, Map<String, ArrayList<String>>> myMap = new ConcurrentHashMap<>();

    public void initializer(Object channel) {
        if (myMap.get(channel) == null) {
            myMap.put(channel, new ConcurrentHashMap<>());
            myMap.get(channel).put("allChannelUser", new ArrayList<>());
            myMap.get(channel).put("onlineUser", new ArrayList<>());
            myMap.get(channel).put("channelHistory", new ArrayList<>());
        }
    }

    public void cleanMap(Object channel){
        myMap.remove(channel);
    }

    @OnOpen
    public void onOpen(Session session) {
        Map map = session.getPathParameters();
        for (Object o : map.keySet()) {
            System.out.println("onOpenServer: " + o + " :  " + map.get(o) + " | id: " + session.getId());

            initializer(map.get(o));
            myMap.get(map.get(o)).get("allChannelUser").add(session.getQueryString());
            myMap.get(map.get(o)).get("onlineUser").add(session.getId());

        }
//        System.out.println("onOpenServerQ: " + session.getQueryString());
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
//        System.out.println("OnMessageServer");
        session.getOpenSessions().forEach((e) ->
                {
                    try {
                        Map map = session.getPathParameters();
                        for (Object o : map.keySet()) {
                            ArrayList<String> usersId = myMap.get(map.get(o)).get("onlineUser");
                            for (String id : usersId) {
//                                System.out.println("id: " + id + " | e.getId: " + e.getId());
                                if (id == e.getId()) {
                                    e.getBasicRemote().sendText("<h5><b style=\"color: red;\">" + session.getQueryString() + "</b> :  " + msg + "</h5>");

                                    if (e.getId() == session.getId()) {
                                        myMap.get(map.get(o)).get("channelHistory").add(msg);
                                    }
                                }
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
        );
    }

    @OnClose
    public void onClose(Session session) {
        Map map = session.getPathParameters();
        for (Object o : map.keySet()) {
            ArrayList<String> usersId = myMap.get(map.get(o)).get("onlineUser");
            usersId.remove(session.getId());
            if (usersId.isEmpty()) {
                ChatService.getInstance().save(myMap.get(map.get(o)).get("allChannelUser"), myMap.get(map.get(o)).get("channelHistory"));
                cleanMap(map.get(o));
            }
        }
//        System.out.println("onCloseServer, id: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
//        System.out.println("OnErrorBeforeOnClose");
    }
}
