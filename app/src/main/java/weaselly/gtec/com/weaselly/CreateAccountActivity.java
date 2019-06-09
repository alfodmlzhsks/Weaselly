package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        binding.btnBack.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnNextStep2:
                    String pw = binding.edtPW.getText().toString();
                    String pwAgain = binding.edtPWAgain.getText().toString();

                    if(!pw.equals(pwAgain)) {
                        Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent nextStep2Intent=new Intent(getApplicationContext(),AddSimpleInfoActivity.class);
                        nextStep2Intent.putExtra("password", pw);
                        startActivity(nextStep2Intent);
                        overridePendingTransition(R.anim.anim_slide_out_left, R.anim.anim_slide_in_right);
                        break;
                    }
                    break;
                case R.id.btnBack:
                    finish();
                    break;
            }

        }
    };

}
