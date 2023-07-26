package com.jun.test02.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jun.test02.R;
import com.jun.test02.databinding.ActivityUpdateUserBinding;
import com.jun.test02.model.Database;
import com.jun.test02.viewmodel.ViewModel;

public class UpdateUserActivity extends AppCompatActivity {

    String TAG = "UPDATE_USER_ACTIVITY";

    ActivityUpdateUserBinding binding;

    ViewModel viewModel;

    Intent receivedIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "updateUserActivity onCreate");
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_user);
        viewModel = new ViewModel(Database.getInstance());
        binding.setViewModel(viewModel);

        receivedIntent = getIntent();

        binding.updateUserEditText.setText(receivedIntent.getStringExtra("targetName"));

        binding.userNameUpdateBtn.setOnClickListener(view -> {
            boolean result = viewModel.UpdateUser(binding.updateUserEditText.getText().toString(), receivedIntent.getLongExtra("targetId", -1));
            if(result) {
                Toast.makeText(getApplicationContext(), "이름 변경 성공! ("+receivedIntent.getStringExtra("targetName") +"->" + binding.updateUserEditText.getText()+")", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "이름 변경 불가능", Toast.LENGTH_SHORT).show();
            }
        });

        binding.backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ViewUserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });


    }
}
