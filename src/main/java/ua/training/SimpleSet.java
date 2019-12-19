package ua.training;

import java.util.Iterator;

public interface SimpleSet<E> {
    boolean add(E e);
    boolean remove(E e);
    boolean contains(E e);
    Iterator iterator();
    int size();
}
