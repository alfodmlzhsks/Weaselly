package weaselly.gtec.com.weaselly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EndInputActivity extends AppCompatActivity {

    Button btnJumpToMain;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_input);

        btnJumpToMain = (Button)findViewById(R.id.btnJumpToMain);
        btnAddData = (Button)findViewById(R.id.btnAddData);

        btnJumpToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InputDataActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
