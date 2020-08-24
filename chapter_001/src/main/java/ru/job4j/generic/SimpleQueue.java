package ru.job4j.generic;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;

    public T poll() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        return rePlace();
    }

    public void push(T value) {
        in.push(value);
        count++;
    }

    private T rePlace() {
        int inCount = 0;
        T res = null;
        for (int i = 0; i <= count; i++) {
            out.push(in.pop());
            count--;
            inCount++;
        }
        res = out.pop();
        inCount--;
        for (int i = 0; i < inCount; i++) {
            in.push(out.pop());
            count++;
        }
        return res;
    }
}