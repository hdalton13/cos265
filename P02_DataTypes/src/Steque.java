import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {
    private Node first, last;
    private int stequeSize=0;

    public Steque() {    }

    private class Node {    // private inner class
        Item item;          // (access modifiers for instance
        Node next;          // variables don't matter)
    }

    // returns the number of items stored
    public int size() {
        return stequeSize;
    }

    // returns true iff steque is empty
    public boolean isEmpty() {
        return first == null;
    }

    // enqueues item to bottom of steque
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;     // handle special case
        else oldlast.next = last;
        stequeSize++;
    }

    // pushes item to top of steque
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        stequeSize++;
    }

    // pops and returns top item
    public Item pop() throws NoSuchElementException {
        if (stequeSize<0) throw new NoSuchElementException();
        else {
            stequeSize--;
            Item item = first.item;
            first = first.next;
            return item;
        }
        //return null;
    }

    // returns new Iterator<Item> that iterates over items in steque
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
    public static void main(String[] args) throws NoSuchElementException {
        Steque<Integer> q = new Steque<>();
        q.enqueue(3);
        q.push(1);

    }
}