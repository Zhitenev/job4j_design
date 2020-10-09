package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        User userOne = new User("Ivan", 2, new GregorianCalendar(1997, 2 , 25));
        User userTwo = new User("Ivan", 2, new GregorianCalendar(1997, 2 , 25));
        //User userTwo = new User("Ivan", 1, new GregorianCalendar(1992, 12 , 11));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        System.out.println(userOne.hashCode());
        System.out.println(userTwo.hashCode());
        System.out.println(map);
    }
}

