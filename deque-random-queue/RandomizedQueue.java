import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] items = (Item[]) new Object[2];
    // construct an empty randomized queue
    public RandomizedQueue(){}

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item toEnqueue){
        if (toEnqueue == null)
            throw new NullPointerException("Can't enqueue null item");
            this.items[size++] = toEnqueue;
            if (size == this.items.length) {
                resize(2 * this.items.length);
            }
            swapItem();
    }

    // remove and return a random item
    public Item dequeue(){
        if (size == 0)
        throw new NoSuchElementException("Can't dequeue, queue is empty");
        Item dequeued = this.items[--size];
        if (size > 0 && size == this.items.length / 4) {
            resize(this.items.length / 2);
        }
        this.items[size] = null;
        return dequeued;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (size == 0) {
            throw new NoSuchElementException("Can't dequeue, queue is empty");
        }
        int i = StdRandom.uniform(size);
        return this.items[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private int i;

        public boolean hasNext() {
            return items[i] != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = items[i++];
                return item;
            }
        }
    }
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
    }
    private void swapItem() {
        int i = StdRandom.uniform(size);
        Item temp = items[i];
        items[i] = items[size - 1];
        items[size - 1] = temp;
    }
    
    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(rq.dequeue());
            else
                rq.enqueue(s);
        }
    }

}