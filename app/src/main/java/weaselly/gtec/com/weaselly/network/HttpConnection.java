package weaselly.gtec.com.weaselly.network;

import java.util.concurrent.TimeUnit;

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
        this.client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    //웹서버 통신
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

    public void requestFirstTest(String matterCode, String answer, String email, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("matterCode", matterCode)
                .add("answer", answer)
                .add("userEmail", email)
                .build();
        Request request = new Request.Builder()
                .url("http://giruk.iptime.org/weaselly/analyze/firstTest")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void requestSecondTest(String email, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("userEmail", email)
                .build();
        Request request = new Request.Builder()
                .url("http://giruk.iptime.org/weaselly/analyze/secondTest")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void requestUpdateWeak(String email, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("userEmail", email)
                .build();
        Request request = new Request.Builder()
                .url("http://giruk.iptime.org/weaselly/analyze/updateWeak")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void requestAnalyze(String email, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("userEmail", email)
                .build();
        Request request = new Request.Builder()
                .url("http://giruk.iptime.org/weaselly/analyze/analyzeTest")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void requestFinalTest(String email, String index, String answer, Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("userEmail", email)
                .add("index", index)
                .add("answer", answer)
                .build();
        Request request = new Request.Builder()
                .url("http://giruk.iptime.org/weaselly/analyze/finalTest")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
