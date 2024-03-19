package controller.user.authenticate;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;



public class LoginWithGoogle extends HttpServlet {
    Dotenv dotenv = Dotenv.configure().load();
    private  String GOOGLE_CLIENT_ID = dotenv.get("GOOGLE_CLIENT_ID");
    private String GOOGLE_CLIENT_SECRET = dotenv.get("GOOGLE_CLIENT_SECRET");
    private String GOOGLE_LINK_GET_TOKEN = dotenv.get("GOOGLE_LINK_GET_TOKEN");
    private String GOOGLE_LINK_AUTH = dotenv.get("GOOGLE_LINK_AUTH");
    private String GOOGLE_REDIRECT_URI = dotenv.get("GOOGLE_REDIRECT_URI");
    private String[] GOOGLE_SCOPES = dotenv.get("GOOGLE_SCOPES").split(", ");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        // Redirect to Google's OAuth 2.0 authorization page
        AuthorizationCodeFlow flow = initializeFlow();
        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(GOOGLE_REDIRECT_URI);
        String authorizationUrlString = authorizationUrl.build();
        resp.sendRedirect(authorizationUrlString);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        return new AuthorizationCodeFlow.Builder(
                // Provide the necessary parameters based on your configuration
                BearerToken.authorizationHeaderAccessMethod(),  // Replace with appropriate access method if needed
                new NetHttpTransport(),  // Replace with your preferred HttpTransport instance
                new GsonFactory(),  // Assuming you have a JSON_FACTORY object
                new GenericUrl(GOOGLE_LINK_GET_TOKEN),
                new ClientParametersAuthentication(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET),
                GOOGLE_CLIENT_ID,
                GOOGLE_LINK_AUTH
        ).setScopes(Arrays.asList(GOOGLE_SCOPES)).build();
    }
}
