package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public static void main(String[] args) {
        User userOne = new User();
        User userTwo = new User();
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        System.out.println(userOne);
        System.out.println(userTwo);
        System.out.println(map);
    }
}