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
            mem.set(Integer.parseInt(id), model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        if (findById(id) != null) {
            mem.remove(Integer.parseInt(id));
            res = true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        T res = null;
        for (int i = 0; i < mem.size(); i++) {
            if (i == Integer.parseInt(id)) {
                res = mem.get(i);
            }
        }
        return res;
    }
}