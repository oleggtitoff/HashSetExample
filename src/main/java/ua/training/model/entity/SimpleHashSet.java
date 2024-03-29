package ua.training.model.entity;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        int index = hashFunction(e.hashCode());
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(e)) {
                removeCurrent(index, current, previous);
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    private void removeCurrent(int index, Entry current, Entry previous) {
        if (previous == null) {
            buckets[index] = current.next;
        } else {
            previous.next = current.next;
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hashFunction(e.hashCode());
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new SimpleHashSetIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();

        appendBuckets(stringBuffer);
        return stringBuffer.toString();
    }

    private void appendBuckets(StringBuffer stringBuffer) {
        for (int index = 0; index < buckets.length; index++) {
            appendIfBucketIsNotEmpty(stringBuffer, index);
        }
    }

    private void appendIfBucketIsNotEmpty(StringBuffer stringBuffer, int index) {
        Entry currentEntry;

        if (buckets[index] != null) {
            currentEntry = buckets[index];
            appendIndex(stringBuffer, index);
            stringBuffer.append(currentEntry.key.toString());
            appendEntryWhileIsNext(currentEntry, stringBuffer);
            stringBuffer.append('\n');
        }
    }

    private void appendIndex(StringBuffer stringBuffer, int index) {
        stringBuffer.append("[");
        stringBuffer.append(index);
        stringBuffer.append("] ");
    }

    private void appendEntryWhileIsNext(Entry currentEntry, StringBuffer stringBuffer) {
        while (currentEntry.next != null) {
            currentEntry = currentEntry.next;
            stringBuffer.append(" -> ");
            stringBuffer.append(currentEntry.key.toString());
        }
    }

    static class Entry<E> {
        E key;
        Entry<E> next;
    }

    public class SimpleHashSetIterator<E> implements Iterator<E> {
        private int currentBucket = -1;
        private int previousBucket = -1;
        private Entry currentEntry = null;
        private Entry previousEntry = null;

        @Override
        public boolean hasNext() {
            if (currentEntry != null && currentEntry.next != null) {
                return true;
            }

            for (int index = currentBucket + 1; index < buckets.length; index++) {
                if (buckets[index] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public E next() {
            previousEntry = currentEntry;
            previousBucket = currentBucket;

            if (currentEntry == null || currentEntry.next == null) {
                currentBucket++;
                moveThroughBuckets();
                assignCurrentEntryIfBucketIsInRange();
            } else {
                currentEntry = currentEntry.next;
            }
            return (E) currentEntry.key;
        }

        private void moveThroughBuckets() {
            while (currentBucket < buckets.length &&
                    buckets[currentBucket] == null) {
                currentBucket++;
            }
        }

        private void assignCurrentEntryIfBucketIsInRange() {
            if (currentBucket < buckets.length) {
                currentEntry = buckets[currentBucket];
            } else {
                throw new NoSuchElementException();
            }
        }

    }

}
