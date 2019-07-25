
// *  Name:
// *  Date:
// *  Description:


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private Item[] q;
    private int N = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;


    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = item;
        if (isEmpty()) last = first;
        else oldFirst.prev = first;
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.prev = oldLast;
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (N > 1) {
            first = first.next;
            // clean up memory
            first.prev = null;
        }
        N--;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = last.item;
        if (N > 1) {
            last = last.prev;
            last.next = null;

        }
        N--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        d.addFirst(2);
        d.addFirst(1);
        d.removeFirst();
        d.removeLast();
        for (Integer i : d) {
            System.out.println(i);
        }

    }

}
