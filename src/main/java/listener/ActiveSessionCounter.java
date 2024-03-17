package listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

public class ActiveSessionCounter implements HttpSessionListener {
    private static AtomicInteger activeSessions = new AtomicInteger();

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        activeSessions.incrementAndGet();
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        if(activeSessions.get() > 0) {
            activeSessions.decrementAndGet();
        }
    }

    public static int getActiveSessions() {
        return activeSessions.get();
    }

    public static void resetActiveSessions() {
        activeSessions.set(0);
    }
}
