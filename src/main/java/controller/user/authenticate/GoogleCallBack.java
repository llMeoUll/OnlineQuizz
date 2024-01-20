package controller.user.authenticate;

        import com.google.api.client.auth.oauth2.*;
        import com.google.api.client.http.GenericUrl;
        import com.google.api.client.http.javanet.NetHttpTransport;
        import com.google.api.client.json.JsonParser;
        import com.google.api.client.json.gson.GsonFactory;
        import com.google.gson.Gson;
        import com.google.gson.JsonObject;
        import dao.UserDBContext;
        import entity.User;
        import jakarta.servlet.*;
        import jakarta.servlet.http.*;
        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.HttpClientBuilder;
        import org.apache.http.util.EntityUtils;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.concurrent.locks.Lock;
        import java.util.concurrent.locks.ReentrantLock;

public class GoogleCallBack extends HttpServlet {
    private static final String REDIRECT_URI = "http://localhost:8888/Quizzicle/callback";
    public String CLIENT_ID = "909571688274-7fcph0l0keo67e5odb56a9u5uivadvru.apps.googleusercontent.com";
    public String CLIENT_SECRET = "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";
    public String LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";
    public String LINK_AUTH = "https://accounts.google.com/o/oauth2/auth";
    private AuthorizationCodeFlow flow;
    private final Lock lock = new ReentrantLock();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        StringBuffer buf = req.getRequestURL();
        if (req.getQueryString() != null) {
            buf.append('?').append(req.getQueryString());
        }
        AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
        String code = responseUrl.getCode();
        if (responseUrl.getError() != null) {
            onError(req, resp, responseUrl);
        } else if (code == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Missing authorization code");
        } else {
            lock.lock();
            try {
                if (flow == null) {
                    flow = initializeFlow();
                }
                String redirectUri = getRedirectUri(req);
                TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
                String userId = getUserId(req);
                Credential credential = flow.createAndStoreCredential(response, userId);
                onSuccess(req, resp, credential);
            } finally {
                lock.unlock();
            }
        }

    }

    private String getUserId(HttpServletRequest request) {
        return null;
    }

    private String getRedirectUri(HttpServletRequest request) {
        return REDIRECT_URI;
    }

    private void onError(HttpServletRequest request, HttpServletResponse response, AuthorizationCodeResponseUrl errorResponse) throws IOException {
        response.getWriter().println("login error!");
    }

    private void onSuccess(HttpServletRequest request, HttpServletResponse response, Credential credential) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://www.googleapis.com/userinfo/v2/me");
        httpGet.setHeader("Authorization", "Bearer " + credential.getAccessToken());
        HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            UserDBContext db = new UserDBContext();
            // Convert the response content to a string
            String responseBody = EntityUtils.toString(entity);
            JsonObject jsonObject = new Gson().fromJson(responseBody, JsonObject.class);
            if (jsonObject.has("id")) {
                jsonObject.remove("id");
            }
            User user = new Gson().fromJson(jsonObject, User.class);
            HttpSession userSession = request.getSession();
            userSession.setAttribute("user", user);
            db.insertGoogleUser(user);
            response.sendRedirect("./");
        } else {
            // Handle the case where the response entity is null
            response.getWriter().println("Error: No response entity");
        }

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

