package weaselly.gtec.com.weaselly;

import android.Manifest;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weaselly.gtec.com.weaselly.bottomSheets.ButtonAnswerBottomSheet;
import weaselly.gtec.com.weaselly.bottomSheets.SubjectiveAnswerBottomSheet;
import weaselly.gtec.com.weaselly.databinding.ActivityTestBinding;
import weaselly.gtec.com.weaselly.network.HttpConnection;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;
    int questionType = 0;
    HttpConnection httpConnection = HttpConnection.getInstance();
    String email = "";
    public static WebView wvShowMun;
    public static int matter_size = 0;
    static String index = "";
    static  WebSettings settings;
    public static String[] matters;

    @Override
    protected void onResume() {
        super.onResume();

        CheckPermission checkPermission=new CheckPermission(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE});
        checkPermission.checkMyPermission();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_training);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        binding.setActivity(this);

        wvShowMun = (WebView)findViewById(R.id.wvShowMun1);
        settings = wvShowMun.getSettings();

        getMatter();



        binding.btnInputAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(questionType){
                    case WeasellyCon.BUTTON_ANSWER:
                        ButtonAnswerBottomSheet buttonAnswerBottomSheet = new ButtonAnswerBottomSheet();
                        buttonAnswerBottomSheet.show(getSupportFragmentManager(),WeasellyCon.BUTTON_ANSWER+"");
                        break;
                    case WeasellyCon.SUBJECTIVE_ANSWER:
                        SubjectiveAnswerBottomSheet subjectiveAnswerBottomSheet = new SubjectiveAnswerBottomSheet();
                        subjectiveAnswerBottomSheet.show(getSupportFragmentManager(),WeasellyCon.SUBJECTIVE_ANSWER+"");
                        break;
                }
            }
        });
    }

    private void getMatter() {
        SharedPreferences preferences = getSharedPreferences("account", MODE_PRIVATE);
        email = preferences.getString("email", "");
        index = preferences.getString("index", "");

        httpConnection.requestSecondTest(email, callback);
    }

    Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.i("tqtq", "casc");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            String body = response.body().string();
            body = body.replaceAll("'", "");
            body = body.replaceAll("]", "");
            body = body.substring(1);

            matters = body.split(",");
            matter_size = matters.length;

            Log.i("qopdjqw", ""+matter_size);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    matterLoad(12);
                }
            });
        }
    };

    public static void matterLoad(int count) {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/matter");

        if(!file.exists()) {
            file.mkdir();
        }


        settings.setJavaScriptEnabled(true);
        wvShowMun.setWebViewClient(new WebViewClientClass());
        wvShowMun.setWebChromeClient(new WebChromeClient());
//        Log.i("tqtqtq", "index:" + index + ", count: " + count);
        wvShowMun.loadUrl("http://giruk.iptime.org/matter/" + index + "/" + count);
//        wvShowMun.loadUrl("https://www.youtube.com/watch?v=W-Ml8wVG6es");

//        f.delete();
    }

    private static class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
