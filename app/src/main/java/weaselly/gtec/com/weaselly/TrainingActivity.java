package weaselly.gtec.com.weaselly;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.security.Permission;

import weaselly.gtec.com.weaselly.bottomSheets.ButtonAnswerBottomSheet;
import weaselly.gtec.com.weaselly.bottomSheets.SubjectiveAnswerBottomSheet;
import weaselly.gtec.com.weaselly.databinding.ActivityTrainingBinding;

public class TrainingActivity extends AppCompatActivity {
    ActivityTrainingBinding binding;
    int questionType = 0;

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

        binding = DataBindingUtil.setContentView(this,R.layout.activity_training);
        binding.setActivity(this);

        questionType = WeasellyCon.BUTTON_ANSWER;
        binding.wvShowMun.loadUrl("file://"+Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/tests.html");

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
