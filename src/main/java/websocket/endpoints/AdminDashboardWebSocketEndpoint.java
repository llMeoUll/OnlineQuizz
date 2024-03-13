package websocket.endpoints;

import entity.Notification;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import entity.User;
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
    public static void notifyAdminsNewUserRegistered(Notification notifications) {
        for (Session session : adminSessions) {
            try {
                session.getBasicRemote().sendText(String.valueOf(notifications.getFrom().getId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}