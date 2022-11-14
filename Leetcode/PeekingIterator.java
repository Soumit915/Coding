package Leetcode;

import java.util.*;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

public class PeekingIterator<T> implements Iterator<T> {

    int ptr;
    List<T> arlist;

    public PeekingIterator(Iterator<T> iterator) {
        // initialize any member here.

        this.arlist = new ArrayList<T>();
        iterator.forEachRemaining((T val) -> {
            this.arlist.add(val);
        });

        this.ptr = -1;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        return arlist.get(ptr + 1);
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        ptr++;
        return arlist.get(ptr);
    }

    @Override
    public boolean hasNext() {
        return ptr < arlist.size() - 1;
    }
}