package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = false;
        if(data.length != 0) {
            if(data[row].length != 0 && data[row].length != column) {
                res = true;
            } else if (data[row].length == column && data[row].length != 0 || data.length != 1) {
                row++;
                while (data[row].length == 0) {
                    row++;
                }
                column = 0;
                res = true;
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            result = data[row][column++];
        }
        return result;
    }
}