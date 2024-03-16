package websocket.endpoints;

import com.google.gson.Gson;
import entity.Notification;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint("/admin/notification")
public class AdminDashboardWebSocketEndpoint {
    private static final Set<Session> adminSessions = new HashSet<>();
    @OnOpen
    public void onOpen(Session session) {
        adminSessions.add(session);
        System.out.println("Admin WebSocket opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message from Admin " + session.getId() + ": " + message);
    }

    @OnClose
    public void onClose(Session session) {
        adminSessions.remove(session);
        System.out.println("Admin WebSocket closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("WebSocket error on Admin session " + session.getId() + ": " + error.getMessage());
    }

    // Gửi thông báo đến tất cả các admin khi có sự kiện đăng ký tài khoản mới
    public static void notifyAdminsNewUserRegistered(Notification notification) {
        for (Session session : adminSessions) {
            try {
                Map<String, String> data = new HashMap<>();
                data.put("info", String.valueOf(notification.getFrom().getId()));
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