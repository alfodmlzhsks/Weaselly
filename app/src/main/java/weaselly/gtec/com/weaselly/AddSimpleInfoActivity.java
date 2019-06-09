package weaselly.gtec.com.weaselly;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import weaselly.gtec.com.weaselly.databinding.ActivityAddSimpleInfoBinding;

public class AddSimpleInfoActivity extends AppCompatActivity {
    ActivityAddSimpleInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_simple_info);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_simple_info);
        binding.setActivity(this);

    }
}
