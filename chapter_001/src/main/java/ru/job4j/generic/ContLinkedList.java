package ru.job4j.generic;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ContLinkedList<T> implements Iterable<T> {
    private Node<T> node;
    private Node<T> head;
    private int modCount = 0;

    public void add(T elem) {
        if (node == null) {
            node = new Node<T>(elem, null);
            this.head = node;
        } else {
            node.next = new Node<T>(elem, null);
            node = node.next;
        }
        modCount++;
    }

    public T get(int index) {
        T res = null;
        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        res = tmp.item;
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new ContLinkedListIterator();
    }

    class ContLinkedListIterator<T> implements Iterator<T> {

        private int expectedModCount = modCount;
        private Node<T> tmp = (Node<T>) head;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T res = tmp.item;
            tmp = tmp.next;
            return res;
        }
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item, Node<T> node) {
            this.item = item;
            this.next = node;
        }
    }
}