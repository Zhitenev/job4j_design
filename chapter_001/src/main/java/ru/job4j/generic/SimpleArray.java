package ru.job4j.generic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private int cap = 10;
    private Object[] obj = new Object[cap];
    private int position = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.obj[index];
    }

    public void add(T model) {
        if (position == this.obj.length - 1) {
            Object[] tmp = new Object[this.obj.length + cap];
            System.arraycopy(this.obj, 0, tmp, 0, this.obj.length);
            this.obj = tmp;
        }
        Objects.checkIndex(position, obj.length);
        this.obj[position++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator();
    }

    class SimpleArrayIterator implements Iterator<T> {
        private int count = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < position;
        }

        @SuppressWarnings("checkstyle:EmptyLineSeparator")
        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) obj[this.count++];
        }
    }
}