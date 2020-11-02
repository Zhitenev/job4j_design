package ru.job4j.map;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private static final int DEFAULT_CAPACITY = 8;
    private Object[][] objects = new Object[DEFAULT_CAPACITY][2];
    private int position = 0;
    private int size = DEFAULT_CAPACITY;

    boolean insert(K key, V value) {
        canInsert();
        boolean res = false;
        int id = hash(key);
        if (objects[id] != null && (V) objects[hash(key)][1] != key) {
            objects[id] = new Object[]{key, value};
            position++;
            res = objects[id] != null;
        }
        return res;
    }

    boolean delete(K key) {
        int id = hash(key);
        objects[id][1] = null;
        position--;
        return objects[id][1] == null;
    }

    V get(K key) {
        V res = null;
        if (objects[hash(key)][1] != null || (V) objects[hash(key)][1] == key) {
            res = (V) objects[hash(key)][1];
        }
        return res;
    }

    private int hash(K key) {
        int id = key.hashCode() % size;
        return id & (size - 1);
    }

    private void canInsert() {
        if (position >= objects.length) {
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
        private int expectedModCount = 0;
        private int nextPosition = 0;
        @Override
        public boolean hasNext() {
            return expectedModCount < position;
        }

        @Override
        public V next() {
            Object[] result = null;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!(expectedModCount < position)) {
                throw new ConcurrentModificationException();
            }
            for (int i = nextPosition; i < objects.length; i++) {
                if (objects[i][1] != null) {
                    result = objects[i];
                    nextPosition = i + 1;
                    expectedModCount++;
                    break;
                }
            }
            return (V) result[1];
        }
    }
}
