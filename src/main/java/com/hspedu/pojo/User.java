package com.hspedu.pojo;


import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private Long uid;

    private Integer id;
    private String name;
    private String type;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Long uid, String name) {
        this.uid = uid;
        this.name = name;
    }


    public User(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static List<User> getList() {
        return getUserList();
    }

    public static List<User> getUserList() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1, "Eric");
        User user2 = new User(2, "John");
        User user3 = new User(3, "Ram");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }


    @Override
    public String toString() {
        Field[] declaredFields = this.getClass().getDeclaredFields();

        StringBuffer sb = new StringBuffer();
        sb.append("User{");
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue != null) {
                sb.append(fieldName).append("=").append(fieldValue).append(" ");
            }
        }

        return sb.append("}").toString();

        // return "User{" +
        //         "id=" + id +
        //         ", name='" + name + '\'' +
        //         ", type='" + type + '\'' +
        //         '}';

    }
}
