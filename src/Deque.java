import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class created by:
 * Author: artyom
 * Date: 16.02.14
 * Time: 16:45
 * Copyright 2014
 * <p/>
 * A double-ended queue or deque (pronounced "deck") is a generalization
 * of a stack and a queue that supports inserting and removing items from either
 * the front or the back of the data structure. Create a generic data type
 * Deque that implements the following API:
 * <p/>
 * Throw a NullPointerException if the client attempts to add a
 * null item;
 * <p/>
 * throw a java.util.NoSuchElementException if the client
 * attempts to remove an item from an empty deque;
 * <p/>
 * throw an UnsupportedOperationException if the client calls the remove()
 * method in the iterator;
 * <p/>
 * throw a java.util.NoSuchElementException if the client calls the
 * next() method in the iterator and there are no more items to return.
 * <p/>
 * Your deque implementation must support each deque operation in
 * constant worst-case time and use space proportional to the number
 * of items currently in the deque. Additionally, your iterator
 * implementation must support the operations next() and hasNext()
 * (plus construction) in constant worst-case time and use a
 * constant amount of extra space per iterator.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque

    public int size() {
        return size;
    }

    // insert the item at the front

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        if (oldFirst != null) oldFirst.prev = first;
        if (last == null) last = first;
        size++;
    }

    // insert the item at the end

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();

        Node oldLast = last;
        last = new Node();

        last.item = item;
        last.prev = oldLast;
        size++;

        if (oldLast != null) oldLast.next = last;
        if (first == null) first = last;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;

        if (first == null) last = null;
        else first.prev = null;

        size--;
        return item;
    }

    // delete and return the item at the end

    public Item removeLast() {
        if (last == null) throw new NoSuchElementException();

        Item item = last.item;
        last = last.prev;

        if (last == null) first = null;
        else last.next = null;

        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ExampleIterator();
    }

    // unit testing
    private class Node {

        Item item;
        Node prev;
        Node next;
    }

    private class ExampleIterator implements Iterator {
        private int nextElement = 0;
        private Node current;

        private ExampleIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return nextElement < size;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Node oldCurrent = current;
                current = current.next;
                nextElement++;
                return oldCurrent.item;
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    public static void main(String[] args) {
    }
}
