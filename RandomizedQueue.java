/* *****************************************************************************
 *  Name: Jacob Collins
 *  Date: 7/26/2019
 *  Description: Randomized Queue implementing LinkedList solution
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> first, current, last;
    private int N = 0;

    // Queue uses LL implementation so we need a node class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
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

        Node<Item> oldLast = last;
        last = new Node<Item>();
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
        // single item list
        if (N == 1) {
            item = first.item;
            first = null;
            current = null;
            N--;
            return item;
        }
        // if the Node is the first of the list
        if (selectedRandomNode == 1) {
            item = first.item;
            // if the node is the only one in the list

            first = first.next;
            first.prev = null;
            N--;
            return item;

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
            for (int i = 1; i < selectedRandomNode; i++) {

                // System.out.println(current.item + "current item is");

                current = current.next;

            }
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
        current = first;

        if (selectedRandomNode == 1) return current.item;

        for (int i = 2; i < selectedRandomNode; i++) {

            current = current.next;
        }
        Item item = current.item;
        current = null;
        return item;
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator(first);
    }

    private class QueueIterator implements Iterator<Item> {


        private Node<Item> current;


        public QueueIterator(Node<Item> first) {
            current = first;
        }

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
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("SDCAYTQYKJ");

        rq.dequeue();

        System.out.println("\nQueue with randomized removal\n");

        for (String i : rq) {
            System.out.println("New Queue: " + i);
        }


    }

}
