package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import weaselly.gtec.com.weaselly.databinding.ActivityInputDataBinding;

public class InputDataActivity extends AppCompatActivity {

    ActivityInputDataBinding binding;

    ArrayAdapter setYearAdapter;
    ArrayAdapter setMonthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_input_data);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_data);
        binding.setActivity(this);



//        setYearAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,WeasellyCon.moYear);
        setYearAdapter = new ArrayAdapter(getApplicationContext(),R.layout.item_spinner_set,WeasellyCon.moYear);
        setMonthAdapter = new ArrayAdapter(getApplicationContext(),R.layout.item_spinner_set,WeasellyCon.moMonth);

        binding.spSetYear.setAdapter(setYearAdapter);
        binding.spSetMonth.setAdapter(setMonthAdapter);



        binding.btnDirectInputAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InputAnswerActivity.class);
                startActivity(intent);
            }
        });


    }
}
