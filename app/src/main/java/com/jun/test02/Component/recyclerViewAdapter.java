package com.jun.test02.Component;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jun.test02.model.Database;
import com.jun.test02.model.Person;
import com.jun.test02.R;

import java.util.ArrayList;


public class recyclerViewAdapter extends RecyclerView.Adapter {
    /*
        어댑터의 동작원리 및 순서
        1.(getItemCount) 데이터 개수를 세어 어댑터가 만들어야 할 총 아이템 개수를 얻는다.
        2.(getItemViewType)[생략가능] 현재 itemview의 viewtype을 판단한다
        3.(onCreateViewHolder) viewpoint 맞는 뷰 홀더를 생성하여 onBindViewHolder에 전달한다.
        4.(onBindViewHolder) 뷰 홀더와 position을 받아 postion에 맞는 데이터를 뷰홀더의 뷰들에 바인딩한다.
    */

    String TAG = "RecyclerViewAdapter";

    // 리사이클러 뷰에 넣을 model 정보 (view - viewmodel - model)
    Database dataModels;

    // 어떤 activity 상황에 해당하는 것인지에 대한 정보 받기
    Context context;

    // 생성자를 통하여 데이터 리스트 context를 받음
    // context는 어떤 Activity에 붙어있는 지에 대한 정보를 알려주는 것이다.
    public recyclerViewAdapter(Context context, Database dataModels){
        this.dataModels = dataModels;
        this.context = context;
    }

    // [재정의] item 수에 대한 정보 제공해야 함
    @Override
    public int getItemCount() {
        //데이터 리스트의 크기를 전달해주어야 함
        return this.dataModels.getPersonListSize();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");

        // 자신이 만든 itemview를 inflate한 다음 뷰홀더 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        // 생선된 뷰 홀더를 리턴하여 onBindViewHolder에 전달한다.
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder");

        MyViewHolder myViewHolder = (MyViewHolder)holder;

        myViewHolder.textView.setText(dataModels.getPersonInOrder(position).getName());
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, position+"번째 텍스트 뷰 클릭", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button editBtn;
        Button deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.personName);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }


}
