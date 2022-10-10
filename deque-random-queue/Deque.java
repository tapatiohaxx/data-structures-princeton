import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    // construct an empty; deque
    public Deque(){}

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        validateItem(item);
        Node originalFirst = first;
        first = new Node();
        first.item = item;
        first.next = originalFirst;
        first.prev = null;
        if (isEmpty())
            last = first;
        else 
            originalFirst.prev = first;
        ++size;        
    }

    // add the item to the back
    public void addLast(Item item){
        validateItem(item);
        Node originalLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = originalLast;
        if(isEmpty())
            first = last;
        else
            originalLast.next = last;
        ++size;

   }

    // remove and return the item from the front
    public Item removeFirst(){
        validateList();
        Item firstRemoved = first.item;
        --size;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return firstRemoved;
    }

    // remove and return the item from the back
    public Item removeLast(){
        validateList();
        Item lastRemoved = last.item;
        --size;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last.next = null;
            last = last.prev;
        }
        return lastRemoved;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    private void validateList() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't remove, list is empty");
        }
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Can't add null pointer");
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> bigDeque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String readIn = StdIn.readString();
            if (readIn.equals("-"))
                StdOut.print(bigDeque.removeLast());
            else
                bigDeque.addFirst(readIn);
        }
    }

}