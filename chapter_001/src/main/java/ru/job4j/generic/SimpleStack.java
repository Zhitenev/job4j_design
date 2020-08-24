package ru.job4j.generic;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T res = linked.deleteLast();
        return res;
    }

    public void push(T value) {
        linked.add(value);
    }
}