package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        if (findById(id) != null) {
            mem.set(findIndex(id), model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        T findItem = findById(id);
        if (findItem != null) {
            mem.remove(findItem);
            res = true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        T res = null;
        for (T t : mem) {
            if (t.getId().equals(id)) {
                res = t;
            }
        }
        return res;
    }

    private int findIndex(String id) {
        int res = 0;
        for (T t: mem) {
            if (t.getId().equals(id)) {
                break;
            }
            res++;
        }
        return res;
    }
}