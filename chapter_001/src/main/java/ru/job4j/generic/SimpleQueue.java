package ru.job4j.generic;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;
    private int outCount = 0;

    public T poll() {
        if (count == 0 && outCount == 0) {
            throw new NoSuchElementException();
        }
        return rePlace();
    }

    public void push(T value) {
        in.push(value);
        count++;
    }

    private T rePlace() {
        if (outCount == 0) {
            for (int i = 0; i <= count; i++) {
                out.push(in.pop());
                count--;
                outCount++;
            }
        }
        outCount--;
        return out.pop();
    }
}