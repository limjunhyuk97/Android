package com.jun.test02.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jun.test02.R;
import com.jun.test02.databinding.ActivityMainBinding;
import com.jun.test02.model.Database;
import com.jun.test02.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // 상속 ViewDataBinding
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // activity_main.xml과 binding이라는 변수를 서로 엮어 준다. (Main Activity와 activity_main 서로 연결)
        Log.d("jun", "mainActivity onCreate() 실행");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main); // Activity Content's View - Layout 연결 & 반환 : ViewDataBinding을 상속하는 제네릭 타입

        // ViewModel과 Activity 간의 양방향 바인딩
        // MainActivity - activity_main.xml - ViewModel 세개가 서로 연결되었다.
        viewModel = new ViewModel(Database.getInstance());
        binding.setViewModel(viewModel);

        // button 클릭시 viewModel.getUser() 메서드 호출되게 정의
        binding.okBtnview.setOnClickListener(view -> {
            Log.d("jun", "버튼 클릭");
            viewModel.updateWinner();
        });

        // button 클릭시 다른 회원추가 페이지로 이동
        binding.addBtnview.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ViewUserActivity.class);
            startActivity(intent);
            finish();
        });
    }

}