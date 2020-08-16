package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleList<T> implements Iterable<T> {

    final private Object[] obj;
    private int position = 0;

    public SimpleList(int size) {
        this.obj = new Object[size];
    }

    public void add(T elem) {
        Objects.checkIndex(position, obj.length);
        this.obj[position++] = elem;
    }

    public void set(int index, T elem) {
        Objects.checkIndex(index, position );
        this.obj[index] = elem;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
        System.arraycopy(this.obj, index + 1, this.obj, index, this.obj.length - 1 - index);
        this.obj[this.obj.length - 1] = null;
        this.position--;
    }

    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.obj[index];
    }

    public int getSize() {
        return this.position;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleListIterator();
    }

    class SimpleListIterator implements Iterator<T> {
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < position;
        }

        @SuppressWarnings("checkstyle:EmptyLineSeparator")
        @Override
        public T next() {
            if (hasNext()) {
                return (T) obj[this.count++];
            }
            throw new NoSuchElementException();
        }
    }
}