package com.jun.test02.model;

// 저장되어야 할 사람 Data 형식을 model에 정의
// id, name 정보 갖고 있으며, getter, setter 정의되어 있음
public class Person {
    private long id;
    private String name;

    private static int incrementedId = 0;

    public Person(String name) {
        this.id = Person.incrementedId++;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}