package websocket.configuration;

import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerApplicationConfig;
import jakarta.websocket.server.ServerEndpointConfig;
import websocket.endpoints.DashboardWebSocketEndpoint;

import java.util.HashSet;
import java.util.Set;

public class DashboardWebSocketEndpointConfiguration implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        Set<ServerEndpointConfig> result = new HashSet<>();
        if(set.contains(DashboardWebSocketEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(DashboardWebSocketEndpoint.class, "/dashboard_endpoint").build());
        }
        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        return null;
    }

}
