package weaselly.gtec.com.weaselly;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.Permission;

import weaselly.gtec.com.weaselly.databinding.ActivityTrainingBinding;

public class TrainingActivity extends AppCompatActivity {
    ActivityTrainingBinding binding;

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

        binding.wvShowMun.loadUrl("file://"+Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/tests.html");


    }
}
