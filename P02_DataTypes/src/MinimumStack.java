import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinimumStack<Item extends Comparable> implements Iterable<Item> {
    private Node first, last;
    private int miniStackSize=0;
    private boolean pushFirstTime = true;
    private Node minimumItem;
    private Node placeHolder= new Node();

    public MinimumStack() { }

    private class Node {    // private inner class
        Item item;          // (access modifiers for instance
        Node next;
        Node pastMinimum;// variables don't matter)
    }

    // returns the number of items stored
    public int size() {
        return miniStackSize;
    }

    // returns true iff empty
    public boolean isEmpty() {
        return first == null;
    }

    // push item onto stack
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        miniStackSize++;

        if(pushFirstTime){
            minimumItem = new Node();
            minimumItem = first;
            pushFirstTime = false;
        } else {
            if (minimumItem.item.compareTo(first.item)>0){
                placeHolder = minimumItem;
                minimumItem = first;
                first.pastMinimum = placeHolder;
            }
        }
    }

    // pop and return the top item
    public Item pop() throws NoSuchElementException {
        if (miniStackSize<0) throw new NoSuchElementException();
        else {
            if (minimumItem.item.compareTo(first.item)>=0){
            minimumItem = first.pastMinimum;
            }

            miniStackSize--;
            Item item = first.item;
            first = first.next;




            return item;
        }
        //return null;
    }

    // returns the minimum item in constant time
    public Item minimum() throws NoSuchElementException {
        if (miniStackSize<0) throw new NoSuchElementException();
        else {
            return minimumItem.item;
        }
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

    public static void main(String[] args) {
        MinimumStack<Integer> name = new MinimumStack<>();
        name.push(8);
        name.push(7);
        name.push(5);
        name.push(10);
        StdOut.println(name.minimum());
        name.pop();
        StdOut.println(name.minimum());
        name.pop();
        StdOut.println(name.minimum());
        StdOut.println(name.size());

    }
}
