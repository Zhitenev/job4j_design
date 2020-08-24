package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T res;
        Node<T> tmp = head;
        Node<T> prev = null;
        if (tmp.next != null) {
            while (tmp.next != null) {
                prev = tmp;
                tmp = tmp.next;
            }
            res = prev.next.value;
            prev.next = null;
        } else {
            res = head.value;
            head = null;
        }
        return res;
    }

    public T get() {
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail.value;
    }

    public void revert() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tmp = head;
        Node<T> res = new Node<>(head.value, null);
        while (tmp.next != null) {
            tmp = tmp.next;
            Node<T> resC = new Node<>(tmp.value, res);
            res = resC;

        }
        head = res;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
