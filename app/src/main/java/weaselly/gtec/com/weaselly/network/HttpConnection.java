package weaselly.gtec.com.weaselly.network;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpConnection {

    private OkHttpClient client;
    private static HttpConnection instance = new HttpConnection();
    public static HttpConnection getInstance() {
        return instance;
    }

    private HttpConnection() {
        this.client = new OkHttpClient();
    }

    /** 웹 서버로 요청을 한다. */
    public void requestRegister(String email, String pw, String name, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("userEmail", email)
                .add("userPW", pw)
                .add("userName", name)
                .build();
        Request request = new Request.Builder()
                .url("http://giruk.iptime.org/weaselly/man/register")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
