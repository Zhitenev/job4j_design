package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class SimpleListTest {
    private SimpleList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOverBoundElements() {
        list.add(4);
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddThreeElementsThenUseDeleteFirstResultTwoAndSizeWillTwo() {
        list.remove(1);
        assertThat(list.get(1), is(3));
        assertThat(list.get(2), is(nullValue()));
    }
}