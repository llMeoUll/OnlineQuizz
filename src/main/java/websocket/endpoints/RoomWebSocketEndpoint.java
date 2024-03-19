package websocket.endpoints;

import com.google.gson.Gson;
import entity.Notification;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.*;
import entity.User;

@ServerEndpoint("/user/room/get/notification")
public class RoomWebSocketEndpoint {
    private static Set<Session> sessions = new HashSet<>();
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }
    @OnMessage
    public void onMessage(String message, Session session) {
    }
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void sendMessageToOtherUsers(Notification notification) {
        for (Session session : sessions) {
            try {
                Map<String, String> data = new HashMap<>();
                data.put("info", notification.getUrl());
                data.put("content", notification.getContent());
                Gson gson = new Gson();
                String jsonData = gson.toJson(data);
                session.getBasicRemote().sendText(jsonData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
