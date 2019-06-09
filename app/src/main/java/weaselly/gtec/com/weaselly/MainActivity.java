package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import weaselly.gtec.com.weaselly.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Intent before;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setActivity(this);

        SharedPreferences preferences = getSharedPreferences("account", MODE_PRIVATE);
        String session = preferences.getString("session", "null");

        if(session.equals("1")) {
            before = getIntent();
            name = before.getStringExtra("name");

            // 회원가입해서 넘어온거라면
            if(name != null) {
                binding.tvTitle.setText(name+", \nWeaselly쌤이 응원한다!");
            } else { //그냥 켰다면
                name = preferences.getString("name", "null");
                binding.tvTitle.setText(name+", \nWeaselly쌤이 응원한다!");
            }


        } else {
            Intent i = new Intent(getApplicationContext(), CreateAccountActivity.class);
            startActivity(i);
            finish();
        }


    }
}
