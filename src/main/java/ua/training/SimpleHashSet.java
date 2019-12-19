package ua.training;

import java.util.Iterator;

public class SimpleHashSet<E> implements SimpleSet<E> {
    private Entry<E>[] buckets;
    private int size = 0;

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private static class Entry<E> {
        E key;
        Entry<E> next;
    }

}
