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
        rePlace();
        count--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        count++;
    }

    private void rePlace() {
        for (int i = 0; i <= count; i++) {
            out.push(in.pop());
        }
    }
}
