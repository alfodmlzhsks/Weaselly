package weaselly.gtec.com.weaselly.bottomSheets;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weaselly.gtec.com.weaselly.R;
import weaselly.gtec.com.weaselly.TestActivity;
import weaselly.gtec.com.weaselly.TrainingActivity;
import weaselly.gtec.com.weaselly.databinding.SheetButtonBinding;
import weaselly.gtec.com.weaselly.network.HttpConnection;

public class ButtonAnswerBottomSheet2 extends BottomSheetDialogFragment implements View.OnClickListener{
    SheetButtonBinding binding;
    ArrayList<Integer> answers;
    int size = TestActivity.matter_size;
    int count = 3;
    boolean isFinish = false;
    HttpConnection httpConnection = HttpConnection.getInstance();
    String email = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.sheet_button,container,false);
        View view = binding.getRoot();

        answers = new ArrayList<>();


        binding.btnInputAnswer1.setOnClickListener(this);
        binding.btnInputAnswer2.setOnClickListener(this);
        binding.btnInputAnswer3.setOnClickListener(this);
        binding.btnInputAnswer4.setOnClickListener(this);
        binding.btnInputAnswer5.setOnClickListener(this);

        SharedPreferences preferences = getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        email = preferences.getString("email", "");

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnInputAnswer1:
                answers.add(1);
                TrainingActivity.matterLoad(count);
                count = count + 3;
                if(count > size) isFinish = true;
                break;
            case R.id.btnInputAnswer2:
                answers.add(2);
                TrainingActivity.matterLoad(count);
                count = count + 2;
                if(count > size) isFinish = true;
                break;
            case R.id.btnInputAnswer3:
                answers.add(3);
                TrainingActivity.matterLoad(count);
                count = count + 5;
                if(count > size) isFinish = true;
                break;
            case R.id.btnInputAnswer4:
                answers.add(4);
                TrainingActivity.matterLoad(count);
                count = count + 3;
                if(count > size) isFinish = true;
                break;
            case R.id.btnInputAnswer5:
                answers.add(5);
                TrainingActivity.matterLoad(count);
                count = count + 5;
                if(count > size) isFinish = true;
                break;
        }

        if(isFinish) {
            String matters = TestActivity.matters[0];
            for(int i=1; i<TestActivity.matter_size; i++) {
                matters = matters + ","+TestActivity.matters[i];
            }

            Integer[] answer = answers.toArray(new Integer[answers.size()]);
            String matterAnswer = "" + answer[0];
            for(int i=1; i<answer.length; i++) {
                matterAnswer = matterAnswer + "," + answer[i];
            }

//            httpConnection.requestFinalTest(email, matters, matterAnswer, callback);
        }
    }

    Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();

            Log.i("tqtq", body);
        }
    };
}
