
/* *****************************************************************************
 *  Name: Jacob Collins
 *  Date:7/20/2019
 *  Description: Percolation class to determine if a system percolates
 *  i.e. if the system is connected from the top to the bottom
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // global variables for the board (siteArray), n (size of board)
    // virtualTopSite and virtualBottomSite
    private boolean[] siteArray;
    private int n, virtualTopSite, virtualBottomSite;
    private WeightedQuickUnionUF wquwpc;

    // constructor that takes n, the size of the board
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        // System.out.println("n is equal to " + n);

        this.n = n;

        siteArray = new boolean[n * n];
        // we need to make the board n *n +2 so that we can add a virtual top and bottom site
        wquwpc = new WeightedQuickUnionUF(n * n + 2);

        virtualTopSite = n * n;
        virtualBottomSite = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) throw new IllegalArgumentException();
        int arrayPosition = (row - 1) * n + (col - 1);
        // if the site is not open, then open it
        if (siteArray[arrayPosition] != true) siteArray[arrayPosition] = true;

        // checks that the row isn't outof bounds on the top side
        if ((row > 1) && (siteArray[arrayPosition - n] != false))
            wquwpc.union(arrayPosition, arrayPosition - n);
        // checks that the col isn't out of bounds on the left side
        if ((col > 1) && (siteArray[arrayPosition - 1] != false))
            wquwpc.union(arrayPosition, arrayPosition - 1);
        // checks that the row isn't out of bounds on the bottom side
        if ((row < n) && (siteArray[arrayPosition + n] != false))
            wquwpc.union(arrayPosition, arrayPosition + n);
        // checks that the column isn't out of boudns on the right side
        if ((col < n) && (siteArray[arrayPosition + 1] != false))
            wquwpc.union(arrayPosition, arrayPosition + 1);

        // checks to see if their should be a connection to the virtualTopSite
        if (row == 1) wquwpc.union(virtualTopSite, arrayPosition);

        // checks to see if their should be a connection to the virtualBottomSite
        if (row == n) wquwpc.union(virtualBottomSite, arrayPosition);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) throw new IllegalArgumentException();
        return siteArray[(row - 1) * n + (col - 1)];


    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ((row < 1) || (col < 1) || (row > n + 1) || (col > n + 1))
            throw new IllegalArgumentException();
        int arrayPosition = (row - 1) * n + (col - 1);
        // see if the position is connected to the virtual top site, if so it is full
        return siteArray[arrayPosition] && wquwpc.connected(arrayPosition, virtualTopSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int i = 0;

        for (int j = 0; j < siteArray.length; j++) {
            if (siteArray[j]) i++;
        }
        return i;
    }

    // does the system percolate?
    public boolean percolates() {
        // is the top connected to the bottom
        return wquwpc.connected(virtualTopSite, virtualBottomSite);
    }



    public static void main(String[] args) {

    }
}
