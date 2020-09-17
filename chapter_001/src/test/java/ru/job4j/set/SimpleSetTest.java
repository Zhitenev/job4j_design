package ru.job4j.set;

import org.junit.Test;
import ru.job4j.generic.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void whenAddThenGet() {
        SimpleSet<String> array = new SimpleSet<>();
        array.add("first");
        array.add("first");
        array.add("second");
        Iterator<String> it = array.iterator();
        it.next();
        String rsl = it.next();
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleSet<String> array = new SimpleSet<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmpty() {
        SimpleSet<String> array = new SimpleSet<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleSet<String> array = new SimpleSet<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}
