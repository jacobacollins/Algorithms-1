/* *****************************************************************************
 *  Name: Jacob Collins
 *  Date: 7/20/2019
 *  Description: Stats for percolation
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
// import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    // global variables
    private int trials;
    private double[] pthreshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        this.trials = trials;


        pthreshold = new double[trials];

        // keeps track of the amount of trials that have been peformed
        for (int trialCounter = 0; trialCounter < trials; trialCounter++) {
            Percolation percolationSimulation = new Percolation(n);
            int openSiteCounter = 0;
            int row = 1 + StdRandom.uniform(n);
            int col = 1 + StdRandom.uniform(n);

            // checks to see if system percolates
            while (percolationSimulation.percolates() == false) {
                percolationSimulation.open(row, col);
                openSiteCounter++;
                while (percolationSimulation.isOpen(row, col)) {
                    row = 1 + StdRandom.uniform(n);
                    col = 1 + StdRandom.uniform(n);
                }
            }
            pthreshold[trialCounter] = (openSiteCounter * 1.0) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(pthreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(pthreshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }


    // test client (see below)
    public static void main(String[] args) {


        int N = 0, T = 0;
        try {
            // way easier than using StdIn
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);

        }
        catch (IllegalArgumentException e) {
        }

        // Stopwatch stopwatch = new Stopwatch();
        PercolationStats statGenerator = new PercolationStats(N, T);
        // System.out.println("Calculations took " + stopwatch.elapsedTime() + "(s)");
        System.out.println("mean                    = " + statGenerator.mean());
        System.out.println("stddev                  = " + statGenerator.stddev());
        System.out.println("95% confidence interval = " + "[" + statGenerator.confidenceLo() +
                                   ", " + statGenerator.confidenceHi() + "]");


    }
}
