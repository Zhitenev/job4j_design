package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleSet = new SimpleArray<>();
    private int position = 0;
    private int modCount = 0;

    void add(E e) {
        if(!cheDoable(e)) {
            simpleSet.add(e);
            position++;
            modCount++;
        }
    }

    private boolean cheDoable(E e) {
        boolean ch = false;
        if(simpleSet != null) {
            for (E tmp : simpleSet) {
                if (Objects.equals(tmp, e)) {
                    ch = true;
                    break;
                }
            }
        }
        return ch;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleSet.SimpleSetIterator();
    }

    class SimpleSetIterator implements Iterator<E> {
        private int count = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < position;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return simpleSet.get(count++);
        }
    }
}