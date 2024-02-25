package websocket.endpoints;

import jakarta.websocket.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DashboardWebSocketEndpoint extends Endpoint {


    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        RemoteEndpoint.Basic remoteEndpoint = session.getBasicRemote();
        session.addMessageHandler(new DashboardNotificationHandler(remoteEndpoint));
    }

    private static class DashboardNotificationHandler implements MessageHandler.Whole<String> {
        private final RemoteEndpoint.Basic remoteEndpoint;

        private DashboardNotificationHandler(RemoteEndpoint.Basic remoteEndpoint) {
            this.remoteEndpoint = remoteEndpoint;
        }

        @Override
        public void onMessage(String message) {
            try {
                if(remoteEndpoint != null) {
                    remoteEndpoint.sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
