package com.jun.test02.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jun.test02.R;
import com.jun.test02.databinding.ActivityAddUserBinding;
import com.jun.test02.model.Database;
import com.jun.test02.viewmodel.ViewModel;

public class AddUserActivity extends AppCompatActivity {

    String TAG = "AddUserActivity";

    ActivityAddUserBinding binding;

    ViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "jun");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        viewModel = new ViewModel(Database.getInstance());
        binding.setViewModel(viewModel);

        // 사용자 추가
        binding.newUserAddBtn.setOnClickListener(view -> {
            boolean successed = viewModel.AddUser(String.valueOf(binding.newUserEditText.getText()));
            Toast toast = Toast.makeText(this, successed ? binding.newUserEditText.getText() : "등록 실패", Toast.LENGTH_SHORT);
            toast.show();
        });

        // 뒤로 가기
        binding.backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ViewUserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

    }
}
