package com.jun.test02.model;

import android.util.Log;

import com.jun.test02.model.Person;
import java.util.ArrayList;
import java.util.logging.*;


// 싱글톤 패턴으로 구현된 데이터베이스
//  - 인스턴스가 하나만 생성됨을 보장한다.
//  - getInstance()를 통해 객체가 있으면 반환하며, 없으면 생성해서 반환한다.
public class Database {

    // Database 객체 내부에 Database Class instance를 private으로 선언해 둔다. (외부에서 접근 불가능)
    private static Database instance;

    // 데이터 저장되는 곳
    private ArrayList<Person> personList = new ArrayList<>();

    // 그때 그때 결정되는 우승자 정보
    private String winner;

    // Nested Interface를 정의하여 내부 인스턴스 생성 : 해당 인스턴스는 onChanged 메서드 호출 가능
    private DatabaseListener databaseListener;

    private Database(){
        Log.d("jun", "Model인 Database 생성");
        personList.add(new Person(0, "최민성"));
        personList.add(new Person(1, "김해준"));
        personList.add(new Person(2, "고현서"));
        personList.add(new Person(3, "문종민"));
        personList.add(new Person(4, "윤상현"));
        winner = "당첨자 확인 버튼을\n눌러주세요!";
    }

    public static Database getInstance() {
        Log.d("jun", "Model에 접근 할 수 있도록 DB 인스턴스 값 요청");
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void getUser() {
        Log.d("jun", "당첨자 획득");
        winner = personList.get((int)(Math.random()*5)).getName();
        notifyChange();
    }

    public int getPersonListSize() {
        return personList.size();
    }

    public Person getPersonInOrder(int number) {
        return personList.get(number);
    }

    // databaseListener.onChanged를 호출하는 메서드
    private void notifyChange() {
        if (databaseListener != null) {
            Log.d("jun", "Model | Data 변경 되어 notify 하라고 알림");
            databaseListener.onChanged();
        }
    }

    // DatabaseListener를 외부에서 정의해줄 수 있도록 해줌
    public void setOnDatabaseListener(DatabaseListener databaseListener) {
        Log.d("jun", "DatabaseListener 구현 객체 참조 변수 세팅 (arg1 : " + databaseListener.getClass().getSimpleName() + ")");
        this.databaseListener = databaseListener;
    }

    // winner 정보를 외부로 알림
    public String getWinner(){
        return winner;
    }

    // 의존성 주입으로 DatabaseListener 메서드의 내용을 받아들인다.
    public interface DatabaseListener {
        void onChanged();
    }
}