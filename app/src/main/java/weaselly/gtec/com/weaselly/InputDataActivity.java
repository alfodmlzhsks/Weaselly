package weaselly.gtec.com.weaselly;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weaselly.gtec.com.weaselly.databinding.ActivityInputDataBinding;
import weaselly.gtec.com.weaselly.network.HttpConnection;

public class InputDataActivity extends AppCompatActivity {

    ActivityInputDataBinding binding;

    ArrayAdapter setYearAdapter;
    ArrayAdapter setMonthAdapter;
    String year = "";
    String month = "";
    HttpConnection httpConnection = HttpConnection.getInstance();
    String email = "";
    String name = "";

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

        binding.btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("account", MODE_PRIVATE);
                email = preferences.getString("email", "");
                name = preferences.getString("name", "");

                httpConnection.requestAnalyze(email, callback);
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

    Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();

            body = body.replaceAll("'", "");
            body = body.replaceAll("]", "");
            body = body.substring(1);

            String[] weaks = body.split(",");
            analyze(weaks);
        }
    };

    private void analyze(final String[] weaks) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<String> listItems = new ArrayList<>();
                for(int i=0; i<weaks.length; i++) {
                    listItems.add(weaks[i].trim());
                }
                final CharSequence[] items = listItems.toArray(new String[listItems.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(InputDataActivity.this);
                builder.setTitle(name + "님의 1차 취약 유형");
                builder.setItems(items, null);
                builder.setNeutralButton("훈련 시작", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), TestActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                builder.show();
            }
        });


    }
}
