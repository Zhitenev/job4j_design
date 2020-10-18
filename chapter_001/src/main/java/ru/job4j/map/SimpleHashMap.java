package ru.job4j.map;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] objects = new Object[DEFAULT_CAPACITY];
    private int position = 0;

    boolean insert(K key, V value) {
        canInsert();
        int id = hash(key);
        objects[id] = value;
        position++;
        return objects[id] != null;
    }

    boolean delete(K key) {
        int id = hash(key);
        objects[id] = null;
        position--;
        return objects[id] == null;
    }

    V get(K key) {
        return (V) objects[hash(key)];
    }

    private int hash(K key) {
        int id = key.hashCode() % objects.length;
        return id & (objects.length - 1);
    }

    private void canInsert() {
        if (position >= objects.length) {
            this.objects = Arrays.copyOf(this.objects, this.position + DEFAULT_CAPACITY);
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new SimpleHashMap.SimpleHashMapIterator();
    }

    class SimpleHashMapIterator implements Iterator<Object> {
        private int expectedModCount = 0;
        private int nextPosition = 0;
        @Override
        public boolean hasNext() {
            return expectedModCount < position;
        }

        @Override
        public V next() {
            Object result = null;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!(expectedModCount < position)) {
                throw new ConcurrentModificationException();
            }
            for (int i = nextPosition; i < objects.length; i++) {
                if (objects[i] != null) {
                    result = objects[i];
                    nextPosition = i + 1;
                    expectedModCount++;
                    break;
                }
            }
            return (V) result;
        }
    }
}
