/* *****************************************************************************
 *  Name: Jacob Collins
 *  Date: 7/26/2019
 *  Description: Randomized Queue implementing LinkedList solution
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, current, last;
    private int N = 0;

    // Queue uses LL implementation so we need a node class
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        current = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.prev = oldLast;
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }


    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        // add one because we want the numbers to line up with the removal of the element
        int selectedRandomNode = (StdRandom.uniform(N) + 1);

        // System.out.println(
        //         "Random Node to be deleted is... " + selectedRandomNode + " and N is... " + N);

        Item item;
        current = first;
        // if the Node is the first of the list
        if (selectedRandomNode == 1) {
            item = first.item;
            // if the node is the only one in the list
            if (N == 1) {
                first = null;
                N--;
                return item;
            }
            first = first.next;
            first.prev = null;
            N--;
            return item;

        }
        // move to the random node
        for (int i = 1; i < selectedRandomNode; i++) {

            // System.out.println(current.item + "current item is");

            current = current.next;

        }

        // if the random node is the last node
        if (selectedRandomNode == N) {
            item = last.item;
            last = last.prev;
            last.next = null;
            N--;
            return item;
        }
        else {

            item = current.item;

            /* These three lines are why we don't need to do an array implementation for the Randomized Queue
             */
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current = null;

            /* now the previous current is no longer linked in the list and can be garbage collected*/

            N--;
            return item;

        }


    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int selectedRandomNode = (StdRandom.uniform(N) + 1);
        Node selected = first;

        if (selectedRandomNode == 1) return selected.item;

        for (int i = 2; i < selectedRandomNode; i++) {

            selected = selected.next;
        }
        return selected.item;
    }


    // return an independent iterator over items in random order
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
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);
        rq.enqueue(10);


        System.out.println("\nQueue with randomized removal\n");
        rq.dequeue();
        for (Integer i : rq) {
            System.out.println("New Queue: " + i);
        }


    }

}
