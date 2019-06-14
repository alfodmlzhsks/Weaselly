package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weaselly.gtec.com.weaselly.databinding.ActivityInputAnswerBinding;
import weaselly.gtec.com.weaselly.network.HttpConnection;

public class InputAnswerActivity extends AppCompatActivity {
    ActivityInputAnswerBinding binding;
    HttpConnection httpConnection = HttpConnection.getInstance();

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
        binding.btnNextQuestion.setOnClickListener(listener);
        binding.btnLongNextQuestion.setOnClickListener(listener);

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

            // 서술형
            if(answerStatus > 20) {
                binding.relLongAnswer.setVisibility(View.VISIBLE);
                binding.relShortAnswer.setVisibility(View.GONE);
            }

            if(answerStatus >28){
                binding.btnInputAnswerSkip.setEnabled(false);
                binding.btnInputAnswer1.setEnabled(false);
                binding.btnInputAnswer2.setEnabled(false);
                binding.btnInputAnswer3.setEnabled(false);
                binding.btnInputAnswer4.setEnabled(false);
                binding.btnInputAnswer5.setEnabled(false);

                String answers = "";
                for(int i=0; i<userAnswer.length; i++) {
                    answers = answers + (userAnswer[i] + " ");
                }

                goFirstTest(answers);

                startActivity(new Intent(getApplicationContext(),EndInputActivity.class));
            }


            binding.tvCurrentCount.setText((answerStatus+1)+"번 문제 정답 입력");


        }
    };

    private void goFirstTest(String answer) {
        SharedPreferences preferences = getSharedPreferences("gichool", MODE_PRIVATE);
        String year = preferences.getString("g_year", "");
        String month = preferences.getString("g_month", "");

        preferences = getSharedPreferences("account", MODE_PRIVATE);
        String email = preferences.getString("email", "");

        if(month.length() == 1) {
            month = "0" + month;
        }

        String matterCode = year + month;

        httpConnection.requestFirstTest(matterCode, answer, email, callback);
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String body = response.body().toString();

        }
    };
}
