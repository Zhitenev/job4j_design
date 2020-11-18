package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final double LOAD_FACTOR = 0.75;
    private Invalue[] objects = new Invalue[DEFAULT_CAPACITY];
    private int position = 0;
    private int size = DEFAULT_CAPACITY;
    public static int modCount = 0;

    boolean insert(K key, V value) {
        canInsert();
        boolean res = false;
        int id = hash(key);
        if (objects[id] == null || objects[id].getKey().equals(key)) {
            objects[id] = new Invalue(key, value);
            position++;
            res = objects[id] != null;
            modCount++;
        }
        return res;
    }

    boolean delete(K key) {
        int id = hash(key);
        if(objects[id] != null && objects[id].getKey().equals(key)) {
            objects[id] = null;
            position--;
            modCount++;
        }
        return objects[id] == null;
    }

    V get(K key) {
        V res = null;
        int id = hash(key);
        if (objects[id] != null && objects[hash(key)].getKey().equals(key)) {
            res = (V) objects[hash(key)].getValue();
        }
        return res;
    }

    private int hash(K key) {
        return key.hashCode() & (size - 1);
    }

    private void canInsert() {
        if (position >= objects.length * LOAD_FACTOR) {
            size += DEFAULT_CAPACITY;
            Invalue[] newArray = new Invalue[size];
            for (Invalue tmp : objects) {
                if(tmp != null && tmp.getKey() != null) {
                    int id = hash((K) tmp.getKey());
                    newArray[id] = tmp;
                }
            }
            this.objects = newArray;
        }
    }

    private class Invalue <K, V> {
        private K key;
        private V value;

        public Invalue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new SimpleHashMap.SimpleHashMapIterator();
    }

    class SimpleHashMapIterator implements Iterator<Object> {
        private final int expectedModCount = modCount;
        private int nextPosition = 0;

        @Override
        public boolean hasNext() {
            return nextPosition < size;
        }

        @Override
        public V next() {
            Invalue result = null;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            for (int i = nextPosition; i < objects.length; i++) {
                if (objects[i] != null) {
                    result = objects[i];
                    nextPosition = i + 1;
                    break;
                }
            }
            return (V) result.getValue();
        }
    }
}
