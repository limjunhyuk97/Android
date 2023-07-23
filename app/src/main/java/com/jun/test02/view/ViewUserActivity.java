package com.jun.test02.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jun.test02.Component.recyclerViewAdapter;
import com.jun.test02.R;
import com.jun.test02.databinding.ActivityViewUserBinding;
import com.jun.test02.model.Database;
import com.jun.test02.viewmodel.ViewModel;



public class ViewUserActivity extends AppCompatActivity {

    // binding 해줄 때, 각 Activity에 묶이는 Binding 인스턴스는 서로 다른 Class 형을 갖는다.
    ActivityViewUserBinding binding;

    ViewModel viewModel;

    RecyclerView recyclerView;

    recyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // activity_add_user.xml과 AddUserActivity를 binding이라는 변수로 연결시켜준다.
        // Activity -> XML
        Log.d("jun", "addUserActivity 로 이동");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_user);

        // ViewModel과 Activity 간의 양방향 바인딩
        // Activity -> ViewModel
        viewModel = new ViewModel(Database.getInstance());
        binding.setViewModel(viewModel);

        // 리사이클러 뷰로 데이터 보여주기
        recyclerView = binding.recylerview;
        adapter = new recyclerViewAdapter(this, Database.getInstance());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // 뒤로 가기 버튼 이벤트 리스너 추가
        binding.backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        // 사용자 추가 버튼 이벤트 리스너 추가
        binding.addUserBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddUserActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
