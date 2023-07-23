package com.jun.test02.model;

import android.util.Log;

import com.jun.test02.model.Person;
import java.util.ArrayList;
import java.util.logging.*;


// 싱글톤 패턴으로 구현된 데이터베이스
//  - 인스턴스가 하나만 생성됨을 보장한다.
//  - getInstance()를 통해 객체가 있으면 반환하며, 없으면 생성해서 반환한다.
public class Database {

    private static Database instance;

    private ArrayList<Person> personList = new ArrayList<>();

    private String winner;

    private DatabaseListener databaseListener;

    //========= 생성자 및 팩토리
    private Database(){
        Log.d("jun", "Model인 Database 생성");
        personList.add(new Person("최민성"));
        personList.add(new Person("김해준"));
        personList.add(new Person("고현서"));
        personList.add(new Person("문종민"));
        personList.add(new Person("윤상현"));
        winner = "당첨자 확인 버튼을\n눌러주세요!";
    }

    public static Database getInstance() {
        Log.d("jun", "Model에 접근 할 수 있도록 DB 인스턴스 값 요청");
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //========= View Model 위한 메서드
    public void updateWinner() {
        Log.d("jun", "당첨자 획득");
        winner = personList.get((int)(Math.random()*getPersonListSize())).getName();
        if(databaseListener != null) databaseListener.onWinnerSelection();
    }

    public boolean addUser(String name) {
        Log.d("jun", "유저 추가");

        Person person = new Person(name);
        return personList.add(person);
    }

    //========= Recycler View Adapter 위한 메서드
    public int getPersonListSize() {
        return personList.size();
    }

    public Person getPersonInOrder(int number) {
        return personList.get(number);
    }


    //========= View 모델에서의 등록 위한 메서드
    public void setOnDatabaseListener(DatabaseListener databaseListener) {
        Log.d("jun", "DatabaseListener 구현 객체 참조 변수 세팅 (arg1 : " + databaseListener.getClass().getSimpleName() + ")");
        this.databaseListener = databaseListener;
    }

    public interface DatabaseListener {
        void onWinnerSelection();

    }

    //========= View 모델에서의 데이터 갱신 위한 메서드
    public String getWinner(){
        return winner;
    }
}