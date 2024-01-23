package controller.user.authenticate;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;



public class LoginWithGoogle extends HttpServlet {
    private static final String REDIRECT_URI = "http://localhost:8888/Quizzicle/callback";
    public String CLIENT_ID = "909571688274-7fcph0l0keo67e5odb56a9u5uivadvru.apps.googleusercontent.com";
    public String CLIENT_SECRET = "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";
    public String LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";
    public String LINK_AUTH = "https://accounts.google.com/o/oauth2/auth";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        // Redirect to Google's OAuth 2.0 authorization page
        AuthorizationCodeFlow flow = initializeFlow();
        AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI);
        String authorizationUrlString = authorizationUrl.build();
        resp.sendRedirect(authorizationUrlString);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    private AuthorizationCodeFlow initializeFlow() throws IOException {
        return new AuthorizationCodeFlow.Builder(
                // Provide the necessary parameters based on your configuration
                BearerToken.authorizationHeaderAccessMethod(),  // Replace with appropriate access method if needed
                new NetHttpTransport(),  // Replace with your preferred HttpTransport instance
                new GsonFactory(),  // Assuming you have a JSON_FACTORY object
                new GenericUrl(LINK_GET_TOKEN),
                new ClientParametersAuthentication(CLIENT_ID, CLIENT_SECRET),
                CLIENT_ID,
                LINK_AUTH
        ).setScopes(Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email")).build();
    }


}
