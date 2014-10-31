package patrickengelkes.com.alleneune;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

/**
 * Created by patrickengelkes on 27/10/14.
 */
public class SessionController {
    public static final String host = "http://192.168.56.1:3000";

    private SessionParams sessionParams;

    private JSONObject loginAnswer;

    public SessionController(SessionParams sessionParams) {
        this.sessionParams = sessionParams;
    }

    public boolean logIn() throws ExecutionException, InterruptedException, JSONException {
        HttpResponse response = new SessionTask().execute(this.sessionParams).get();
        if (response != null) {
            loginAnswer = new JsonBuilder().execute(response).get();
            if (response.getStatusLine().getStatusCode() == 201) {
                return true;
            }
        }
        return false;
    }

    public JSONObject getLoginAnswer() {
        return this.loginAnswer;
    }

    private class SessionTask extends AsyncTask<SessionParams, Integer, HttpResponse> {

        @Override
        protected HttpResponse doInBackground(SessionParams... sessionParams) {
            SessionParams  sessionParam = sessionParams[0];
            try {
                JSONObject session = new JSONObject();
                String json = sessionParam.getJsonString();
                StringEntity stringEntity = new StringEntity(json);

                HttpPost httpPost = new HttpPost(SessionController.host + "/sessions");
                httpPost.setEntity(stringEntity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-Type", "application/json");
                return new DefaultHttpClient().execute(httpPost);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


