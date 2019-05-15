package com.course.model;

import lombok.Data;

@Data
public class AddUserCase {

    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;
    private String expected;

    @Override
    public String toString() {
        return ("{" +
                "id:" + id +
                ", username:" + userName +
                ", password:" + password +
                ", age:" + age +
                ", sex:" + sex +
                ", permission:" + permission +
                ", isDelete:" + isDelete +
                ", expected:" + expected +
                "}");
    }
}
