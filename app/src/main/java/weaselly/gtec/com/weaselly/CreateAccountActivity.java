package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import weaselly.gtec.com.weaselly.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_account);
        binding.setActivity(this);

        binding.btnNextStep2.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnNextStep2:
                    Intent nextStep2Intent=new Intent(getApplicationContext(),AddSimpleInfoActivity.class);
                    startActivity(nextStep2Intent);
                    overridePendingTransition(R.anim.anim_slide_out_left, R.anim.anim_slide_in_right);
                    break;
            }

        }
    };
}
