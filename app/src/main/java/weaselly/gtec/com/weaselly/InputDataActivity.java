package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import weaselly.gtec.com.weaselly.databinding.ActivityInputDataBinding;

public class InputDataActivity extends AppCompatActivity {

    ActivityInputDataBinding binding;

    ArrayAdapter setYearAdapter;
    ArrayAdapter setMonthAdapter;
    String year = "";
    String month = "";

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

        binding.spSetYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spSetMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnDirectInputAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
                Intent intent = new Intent(getApplicationContext(),InputAnswerActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void setDate() {
        SharedPreferences preferences = getSharedPreferences("gichool", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("g_year", year);
        editor.putString("g_month", month);
        editor.commit();
    }
}
