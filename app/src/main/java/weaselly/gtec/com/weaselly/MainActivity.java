package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

//                Intent i = new Intent(getApplicationContext(), UpdateWeakService.class);
//                startService(i);
            } else { //그냥 켰다면
                name = preferences.getString("name", "null");
                binding.tvTitle.setText(name+", \nWeaselly쌤이 응원한다!");

//                Intent i = new Intent(getApplicationContext(), UpdateWeakService.class);
//                startService(i);
            }
        } else {
            Intent i = new Intent(getApplicationContext(), CreateAccountActivity.class);
            startActivity(i);
            finish();
        }


        binding.btnAnalyze.setOnClickListener(listener);
        binding.btnTraining.setOnClickListener(listener);
        binding.btnTest.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = null;
            switch(v.getId()) {
                case R.id.btnAnalyze:
                    i = new Intent(getApplicationContext(), InputDataActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case R.id.btnTraining:
                    i = new Intent(getApplicationContext(), TrainingActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case R.id.btnTest:
                    i = new Intent(getApplicationContext(), TestActivity.class);
                    startActivity(i);
                    finish();
                    break;
            }
        }
    };
}
