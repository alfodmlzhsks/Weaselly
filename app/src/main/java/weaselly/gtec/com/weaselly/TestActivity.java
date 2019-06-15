package weaselly.gtec.com.weaselly;

import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import weaselly.gtec.com.weaselly.bottomSheets.ButtonAnswerBottomSheet;
import weaselly.gtec.com.weaselly.bottomSheets.SubjectiveAnswerBottomSheet;
import weaselly.gtec.com.weaselly.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;
    int questionType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_test);
        binding.setActivity(this);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_training);
        binding.setActivity(this);

        questionType = WeasellyCon.BUTTON_ANSWER;
        binding.wvShowMun.loadUrl("file://"+ Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/tests.html");

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
}
