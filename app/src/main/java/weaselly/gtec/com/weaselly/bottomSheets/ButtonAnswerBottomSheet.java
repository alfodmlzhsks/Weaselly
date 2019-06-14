package weaselly.gtec.com.weaselly.bottomSheets;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weaselly.gtec.com.weaselly.R;
import weaselly.gtec.com.weaselly.databinding.SheetButtonBinding;

public class ButtonAnswerBottomSheet extends BottomSheetDialogFragment {
    SheetButtonBinding binding;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.sheet_button,container,false);
        View view = binding.getRoot();


        return view;


    }
}
