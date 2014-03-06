import java.util.Iterator;

/**
 * Class created by:
 * Author: artyom
 * Date: 16.02.14
 * Time: 22:00
 * Copyright 2014
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen
 * uniformly at random from items in the data structure. Create a generic data type RandomizedQueue that implements
 * the following API:
 * throw a NullPointerException if the client attempts to add a null item; throw a java.util.NoSuchElementException
 * if the client attempts to sample or dequeue an item from an empty randomized queue;
 * <p/>
 * throw an UnsupportedOperationException if the client calls the remove() method in the iterator;
 * <p/>
 * throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more
 * items to return.
 * <p/>
 * Your randomized queue implementation must support each randomized queue operation (besides creating an iterator)
 * in constant amortized time and use space proportional to the number of items currently in the queue.
 * That is, any sequence of M randomized queue operations (starting from an empty queue) should take at most cM
 * steps in the worst case, for some constant c. Additionally, your iterator implementation must support construction
 * in time linear in the number of items and it must support the operations next() and hasNext() in constant
 * worst-case time; you may use a linear amount of extra memory per iterator.
 * The order of two or more iterators to the same randomized queue should be mutually independent;
 * each iterator must maintain its own random order.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue = (Item[]) new Object[1];
    private int last = -1;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the queue empty?
    public boolean isEmpty() {
        return last+1 == 0;
    }

    // return the number of items on the queue
    public int size() {
        return last+1;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();

        if (last + 1 == queue.length) resize(queue.length * 2);

        queue[++last] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        if (size() == 0) throw new java.util.NoSuchElementException();

        int indexToDequeue = (last!=0) ? StdRandom.uniform(last): 0 ;
        Item itemToDequeue = queue[indexToDequeue];
        queue[indexToDequeue] = queue[last];
        queue[last] = null; //avoid loitering
        last--;

        if ( last+1 == queue.length/4 ) resize(queue.length / 2);

        return itemToDequeue;
    }

    // return (but do not delete){} a random item
    public Item sample() {
        if (size() == 0) throw new java.util.NoSuchElementException();

        int randomIndex = (last!=0) ? StdRandom.uniform(last): 0;
        return queue[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIndexArrayIterator();
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
//        for (int i = 0; i < queue.length; i++) copy[i] = queue[i];
        System.arraycopy(queue, 0, copy, 0, queue.length);
        queue = copy;
    }

    private class RandomIndexArrayIterator implements Iterator {

        private int[] indexesOrder;
        private int currentItem = 0;

        private RandomIndexArrayIterator() {
            int size = size();
            indexesOrder = new int[size];
            for (int i = 0; i < size; i++) {
                indexesOrder[i] = StdRandom.uniform(size - 1);
            }

        }

        @Override
        public boolean hasNext() {
            return currentItem < indexesOrder.length;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return queue[indexesOrder[currentItem++]];
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
      //todo strange bug
//    private class ExampleMutualIterator implements Iterator {
//        private Item[] iterationQueue;
//        private int elementsInQueue;
//
//        private ExampleMutualIterator() {
//            iterationQueue = queue;
//            elementsInQueue = size();
//        }
//
//        @Override
//        public boolean hasNext() {
//
//            return elementsInQueue != 0;
//        }
//
//        @Override
//        public Item next() {
//            if (hasNext()) {
//                int indexToDequeue = StdRandom.uniform(elementsInQueue);
//                Item itemToDequeue = iterationQueue[indexToDequeue];
//                iterationQueue[indexToDequeue] = iterationQueue[elementsInQueue-1];
//                iterationQueue[elementsInQueue - 1] = null; //avoid loitering
//                elementsInQueue--;
//                return itemToDequeue;
//            } else {
//                throw new java.util.NoSuchElementException();
//            }
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//
//    }


    // unit testing
    public static void main(String[] args) {
         RandomizedQueue<Integer> queue1 = new RandomizedQueue<Integer>();
        queue1.enqueue(1);
        queue1.enqueue(2);

        queue1.enqueue(3);
        queue1.sample();
        queue1.sample();
        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();
    //    queue1.dequeue();
        queue1.enqueue(3);
        queue1.enqueue(4);
        queue1.enqueue(StdRandom.uniform(20));
        queue1.enqueue(StdRandom.uniform(20));
        queue1.enqueue(StdRandom.uniform(20));
        queue1.dequeue();
        queue1.enqueue(StdRandom.uniform(20));
        queue1.dequeue();
        queue1.dequeue();

        queue1.enqueue(StdRandom.uniform(20));
        queue1.enqueue(StdRandom.uniform(20));

        queue1.enqueue(StdRandom.uniform(20));
        queue1.enqueue(StdRandom.uniform(20));

        queue1.dequeue();

        Iterator<Integer> iterator = queue1.iterator();
        Iterator<Integer> iterator1 = queue1.iterator();
          while (iterator.hasNext()){
              System.out.println(iterator.next());
              if(iterator1.hasNext())System.out.println(iterator1.next());
          }
    }
}

