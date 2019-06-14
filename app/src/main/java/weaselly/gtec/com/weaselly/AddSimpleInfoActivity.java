package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weaselly.gtec.com.weaselly.databinding.ActivityAddSimpleInfoBinding;
import weaselly.gtec.com.weaselly.network.HttpConnection;

public class AddSimpleInfoActivity extends AppCompatActivity {
    ActivityAddSimpleInfoBinding binding;
    Intent before;
    HttpConnection httpConnection = HttpConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_simple_info);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_simple_info);
        binding.setActivity(this);

        before = getIntent();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = binding.edtName.getText().toString();
                final String email = binding.edtEmail.getText().toString();
                final String pw = before.getStringExtra("password");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        httpConnection.requestRegister(email, pw, name, callback);
                    }
                }).start();
            }
        });
    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("add_error", "콜백오류");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String body = response.body().string();
            final String name = body.split(", ")[0];
            final String email = body.split(", ")[1];

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!body.equals("")) {
                        SharedPreferences preferences = getSharedPreferences("account", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putString("session", "1");
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("name", name);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    };
}
