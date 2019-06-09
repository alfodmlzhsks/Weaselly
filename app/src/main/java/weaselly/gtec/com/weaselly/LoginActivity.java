package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import weaselly.gtec.com.weaselly.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setActivity(this);

        eventListener();



    }

    private void eventListener(){
        binding.btnCreateAccount.setOnClickListener(listener);
        binding.btnLogin.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnCreateAccount:
                    Intent createIntent=new Intent(getApplicationContext(),CreateAccountActivity.class);
                    startActivity(createIntent);
                    overridePendingTransition(R.anim.anim_slide_out_left, R.anim.anim_slide_in_right);
                    break;

                case R.id.btnLogin:
                    Intent mainIntent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    break;
            }

        }
    };
}
