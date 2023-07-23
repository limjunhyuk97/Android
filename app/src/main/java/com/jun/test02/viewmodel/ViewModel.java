package com.jun.test02.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;

import com.jun.test02.model.Database;
import com.jun.test02.model.Person;

import java.util.ArrayList;
import java.util.List;

// Activity가 파괴되어 재구성되어도, ViewModel이 데이터를 들고 있다.
public class ViewModel extends BaseObservable {
    private Database database;

    private String winner;

    public ViewModel(Database database){

        Log.d("jun", "ViewModel 생성자 실행 | DB(Model) 참조");

        this.database = database;
        this.winner = database.getWinner();

        // 의존성 주입
        this.database.setOnDatabaseListener(new Database.DatabaseListener() {
            @Override
            public void onWinnerSelection() {
                winner = database.getWinner();

                // BaseObservable의 메서드 : 데이터 변경을 알리고, 데이터 바인딩을 갱신
                // 즉, winner값이 database로부터 데이터를 받아와 바뀐다면, notifyChange()가 UI 업데이트 진행
                // 즉, winner값을 database로부터 받아와 변경한 뒤, 데이터 바인딩 시스템에 의해 @{viewModel.winner} 에 바인딩된 뷰 업데이트
                notifyChange();
            }
        });
    }

    public void updateWinner() {
        database.updateWinner();
    }

    public boolean AddUser(String name) {
        return database.addUser(name);
    }


    public String getWinner(){
        Log.d("jun", "winner 반환");
        return winner;
    }
}