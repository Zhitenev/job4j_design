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
        int index = findIndex(id);
        if (index != -1) {
            mem.set(index, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        int index = findIndex(id);
        if (index != -1) {
            mem.remove(index);
            res = true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        T res = null;
        int index = findIndex(id);
        if (index != -1) {
            res =  mem.get(index);
        }
        return res;
    }

    private int findIndex(String id) {
        int i;
        int res = -1;
        for (i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                i++;
                break;
            }
        }
        res += i;
        return res;
    }
}