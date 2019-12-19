package ua.training;

import java.util.Iterator;

public class SimpleHashSet<E> implements SimpleSet<E> {
    private Entry[] buckets;
    private int size = 0;

    public SimpleHashSet(int capacity) {
        buckets = new Entry[capacity];
    }

    @Override
    public boolean add(E e) {
        int index = hashFunction(e.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(e)) {
                return false;
            }
            current = current.next;
        }

        Entry entry = new Entry();

        entry.key = e;
        entry.next = buckets[index];
        buckets[index] = entry;
        size++;
        return true;
    }

    private int hashFunction(int hashCode) {
        int index = hashCode;

        if (index < 0) {
            index = -index;
        }
        return index % buckets.length;
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
