package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import weaselly.gtec.com.weaselly.databinding.ActivityInputAnswerBinding;

public class InputAnswerActivity extends AppCompatActivity {
    ActivityInputAnswerBinding binding;

    int[] userAnswer = new int[30];
    int answerStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_input_answer);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_input_answer);
        binding.setActivity(this);

        eventListener();

        binding.tvCurrentCount.setText((answerStatus+1)+"번 문제 정답 입력");


    }


    private void eventListener(){
        binding.btnInputAnswer1.setOnClickListener(listener);
        binding.btnInputAnswer2.setOnClickListener(listener);
        binding.btnInputAnswer3.setOnClickListener(listener);
        binding.btnInputAnswer4.setOnClickListener(listener);
        binding.btnInputAnswer5.setOnClickListener(listener);
        binding.btnInputAnswerSkip.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int data = 0;

            switch (v.getId()){

                case R.id.btnInputAnswer1:
                    data=1;
                    break;
                case R.id.btnInputAnswer2:
                    data=2;
                    break;
                case R.id.btnInputAnswer3:
                    data=3;
                    break;
                case R.id.btnInputAnswer4:
                    data=4;
                    break;
                case R.id.btnInputAnswer5:
                    data=5;
                    break;
                case R.id.btnInputAnswerSkip:
                    data = -1;
                    break;

            }

            userAnswer[answerStatus]=data;
            answerStatus++;

            if(answerStatus >28){
                binding.btnInputAnswerSkip.setEnabled(false);
                binding.btnInputAnswer1.setEnabled(false);
                binding.btnInputAnswer2.setEnabled(false);
                binding.btnInputAnswer3.setEnabled(false);
                binding.btnInputAnswer4.setEnabled(false);
                binding.btnInputAnswer5.setEnabled(false);

                startActivity(new Intent(getApplicationContext(),EndInputActivity.class));
            }


            binding.tvCurrentCount.setText((answerStatus+1)+"번 문제 정답 입력");


        }
    };
}
