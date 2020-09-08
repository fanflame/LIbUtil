package com.fanyiran.utils.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fanyiran.utils.R;

public class TipDialogFragment extends DialogFragment implements View.OnClickListener {
    TextView tvTitle;
    TextView tvContent;
    TextView tvCancel;
    TextView tvConfirm;
    private String title;
    private String content;
    private String cancel = "取消";
    private String confirm = "确定";
    private OnDialogClickListener onDialogClickListener;

    public static TipDialogFragment getInstance(String title, String content, String cancel, String confirm,
                                                OnDialogClickListener onDialogClickListener) {
        TipDialogFragment tipFragment = new TipDialogFragment();
        tipFragment.title = title;
        tipFragment.cancel = cancel;
        tipFragment.confirm = confirm;
        tipFragment.content = content;
        tipFragment.onDialogClickListener = onDialogClickListener;
        return tipFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tips, container);
//        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvContent = view.findViewById(R.id.tvContent);
        tvCancel = view.findViewById(R.id.tvCancel);
        tvConfirm = view.findViewById(R.id.tvConfirm);
        tvConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvTitle.setText(title);
        tvConfirm.setText(confirm);
        tvContent.setText(content);
        tvCancel.setText(cancel);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvCancel) {
            if (onDialogClickListener != null) {
                onDialogClickListener.onCancelClick();
            }
        } else if (v.getId() == R.id.tvConfirm) {
            if (onDialogClickListener != null) {
                onDialogClickListener.onConfirmClick();
            }
        }
    }

    public interface OnDialogClickListener {
        void onConfirmClick();

        void onCancelClick();
    }

}
