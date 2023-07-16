package com.jun.test02.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jun.test02.R;
import com.jun.test02.databinding.ActivityAddUserBinding;
import com.jun.test02.databinding.ActivityMainBinding;
import com.jun.test02.model.Database;
import com.jun.test02.viewmodel.ViewModel;

public class AddUserActivity extends AppCompatActivity {

    // binding 해줄 때, 각 Activity에 묶이는 Binding 인스턴스는 서로 다른 Class 형을 갖는다.
    ActivityAddUserBinding binding;

    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // activity_add_user.xml과 AddUserActivity를 binding이라는 변수로 연결시켜준다.
        // Activity -> XML
        Log.d("jun", "addUserActivity 로 이동");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);

        // ViewModel과 Activity 간의 양방향 바인딩
        // Activity -> ViewModel
        viewModel = new ViewModel(Database.getInstance());
        binding.setViewModel(viewModel);

    }
}
