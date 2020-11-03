package ru.job4j.map;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final double LOAD_FACTOR = 0.75;
    private static final int KEY = 0;
    private static final int VALUE = 1;
    private Object[][] objects = new Object[DEFAULT_CAPACITY][2];
    private int position = 0;
    private int size = DEFAULT_CAPACITY;
    public static int modCount = 0;

    boolean insert(K key, V value) {
        canInsert();
        boolean res = false;
        int id = hash(key);
        if (objects[id] != null || !objects[hash(key)][KEY].equals(key)) {
            objects[id] = new Object[]{key, value};
            position++;
            res = objects[id] != null;
            modCount++;
        }
        return res;
    }

    boolean delete(K key) {
        int id = hash(key);
        if(objects[id][KEY].equals(key)) {
            objects[id][VALUE] = null;
            position--;
            modCount++;
        }
        return objects[id][VALUE] == null;
    }

    V get(K key) {
        V res = null;
        if (objects[hash(key)][VALUE] != null || objects[hash(key)][KEY].equals(key)) {
            res = (V) objects[hash(key)][VALUE];
            modCount++;
        }
        return res;
    }

    private int hash(K key) {
        return key.hashCode() & (size - 1);
    }

    private void canInsert() {
        if (position >= objects.length * LOAD_FACTOR) {
            size *= 2;
            Object[][] newArray = new Object[this.position + DEFAULT_CAPACITY][2];
            for (Object[] tmp : objects) {
                if(tmp[0] != null) {
                    int id = hash((K) tmp[0]);
                    newArray[id] = tmp;
                }
            }
            this.objects = newArray;
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
            Object[] result = null;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            for (int i = nextPosition; i < objects.length; i++) {
                if (objects[i][VALUE] != null) {
                    result = objects[i];
                    nextPosition = i + 1;
                    break;
                }
            }
            return (V) result[1];
        }
    }
}
