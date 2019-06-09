package weaselly.gtec.com.weaselly;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import weaselly.gtec.com.weaselly.databinding.ActivityInputDataBinding;

public class InputDataActivity extends AppCompatActivity {

    ActivityInputDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_input_data);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_data);
        binding.setActivity(this);


    }
}
