import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OnePointerQueue<Item> implements Iterable<Item> {
    private Node first, last;
    private int pointerSize=0;

    public OnePointerQueue() { } //constructor

    private class Node {    // private inner class
        Item item;          // (access modifiers for instance
        OnePointerQueue.Node next;          // variables don't matter)
    }
    // returns the number of items stored
    public int size() {
        return pointerSize;
    }

    // returns true iff empty
    public boolean isEmpty() {
        return first == null;
    }

    // enqueue item to "back"
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;     // handle special case
        else oldlast.next = last;
        pointerSize++;
    }

    // dequeue item from "front"
    public Item dequeue() throws NoSuchElementException {
        if (pointerSize<0) throw new NoSuchElementException();
        else{
            pointerSize--;
            /*String item = first.item;
            first = first.next;
            if(isEmpty()) last = null;      // handle special case...
            return item;*/
        }
        return null;
    }

    // returns new Iterator<Item> that iterates over items
    @Override
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public void remove()     { /* not supported */ }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // perform unit testing here
    public static void main(String[] args) {

    }
}
