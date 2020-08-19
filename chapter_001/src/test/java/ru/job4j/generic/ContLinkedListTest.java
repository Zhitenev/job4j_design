package ru.job4j.generic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ContLinkedListTest {
    @Test
    public void whenAddThenGet() {
        ContLinkedList<String> array = new ContLinkedList<>();
        array.add("first");
        array.add("second");
        array.add("third");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
        rsl = array.get(1);
        assertThat(rsl, is("second"));
        rsl = array.get(2);
        assertThat(rsl, is("third"));
    }

    @Test
    public void whenAddThenIt() {
        ContLinkedList<String> array = new ContLinkedList<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        ContLinkedList<String> array = new ContLinkedList<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        ContLinkedList<String> array = new ContLinkedList<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}