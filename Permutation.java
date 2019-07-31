/* *****************************************************************************
 *  Name: Jacob Collins
 *  Date: 7-26-2019
 *  Description: Permutation using the Randomized Queue with LL
 *  uncomment for Stopwatch to show times
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> strings = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            strings.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        // Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < k; i++) {
            StdOut.println(strings.dequeue());
        }
        // System.out.println(stopwatch.elapsedTime());
    }
}
