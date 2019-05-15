package com.course.model;

import lombok.Data;

@Data
public class LoginCase {

    private int id;
    private String userName;
    private String password;
    private String expected;

    @Override
    public String toString() {
        return ("{" +
                "id:" + id +
                ", username:" + userName  +
                ", password:" + password +
                ", expected:" + expected  +
                "}");
    }
}
