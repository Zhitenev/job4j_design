package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {
    @Test
    public void whenInsertInSimpleMapAndGetSecond() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        assertThat(simpleHashMap.get("second"), is(2));
    }

    @Test
    public void whenInsertInSimpleMapAndGetThird() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        assertThat(simpleHashMap.get("third"), is(3));
    }

    @Test
    public void whenInsertInSimpleMapAndGetFirst() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        assertThat(simpleHashMap.get("first"), is(1));
    }

    @Test
    public void whenInsertInSimpleMapAndGetFirstAfterSecondInsert() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        simpleHashMap.insert("first", 5);
        assertThat(simpleHashMap.get("first"), is(5));
    }

    @Test
    public void whenInsertInSimpleMapAndDeleteSecond() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        simpleHashMap.delete("second");
        assertThat(simpleHashMap.get("second"), is(nullValue()));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIterator() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        Iterator iterator = simpleHashMap.iterator();
        assertThat(iterator.next(), is(1));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIteratorForTwoElem() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        Iterator iterator = simpleHashMap.iterator();
        iterator.next();
        assertThat(iterator.next(), is(2));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIteratorForThreeElem() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        Iterator iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(3));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIteratorForFourElem() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        simpleHashMap.insert("four", 4);
        Iterator iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(4));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIteratorForFourElemIntegerKey() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(2, 2);
        simpleHashMap.insert(3, 3);
        simpleHashMap.insert(4, 4);
        Iterator iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(3));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIteratorHasNextTrue() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        Iterator iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenInsertInSimpleMapAndDUseIteratorHasNextFalse() {
        SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("first", 1);
        simpleHashMap.insert("second", 2);
        simpleHashMap.insert("third", 3);
        Iterator iterator = simpleHashMap.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }
}
