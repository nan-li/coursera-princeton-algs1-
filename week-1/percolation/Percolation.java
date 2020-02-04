/* *****************************************************************************
 *  Name: Nan Li
 *  Date: Feb 1, 2020
 *  Description: To be determined
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private int sitesOpened;
    private final WeightedQuickUnionUF uf;
    private byte[] states;
    private boolean doesPerc;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        // Check that n > 0
        if (n < 1)
            throw new IllegalArgumentException("size n must be greater than 0");

        // Create UF structure where 0 is virtual top site and n*n+1 is bottom site
        uf = new WeightedQuickUnionUF(n * n + 1);
        states = new byte[n * n + 1];
        size = n;
        sitesOpened = 0;

        /*
        for (int i = 1; i <= n; i++) {
            uf.union(0, i);
            uf.union(n * n + 1, n * (n - 1) + i);
        }
        */

    }


    public static void main(String[] args) {
        // to do
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        // Check if row and col within range 1 to n
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException("row or column out of bounds");

        int index = size * (row - 1) + col;

        // Check this OPEN bit is set
        if ((states[index] & 1) == 1)
            return;

        // Set this to OPEN
        states[index] = (byte) (states[index] | 1);
        sitesOpened++;

        /*
        // Union with top virtual node
        if (index <= size)
            uf.union(index, 0);

        // Union with bottom virtual node
        if (index > size * (size - 1))
            uf.union(index, size * size + 1);

         */

        byte temp;

        // Union with left
        if (index % size != 1)
            if ((states[index - 1] & 1) == 1) {
                temp = (byte) (states[uf.find(index)] | states[uf.find(index - 1)]);
                uf.union(index, index - 1);
                states[uf.find(index)] = temp;
            }


        // Union with right
        if (index % size != 0)
            if ((states[index + 1] & 1) == 1) {
                temp = (byte) (states[uf.find(index)] | states[uf.find(index + 1)]);
                uf.union(index, index + 1);
                states[uf.find(index)] = temp;
            }


        // Union with top
        if (index > size)
            if ((states[index - size] & 1) == 1) {
                temp = (byte) (states[uf.find(index)] | states[uf.find(index - size)]);
                uf.union(index, index - size);
                states[uf.find(index)] = temp;
            }


        // Union with bottom
        if (index <= size * (size - 1))
            if ((states[index + size] & 1) == 1) {
                temp = (byte) (states[uf.find(index)] | states[uf.find(index + size)]);
                uf.union(index, index + size);
                states[uf.find(index)] = temp;
            }


        // Check if THIS is in top row
        if (index <= size)
            // Set root CONNECTED_TO_TOP
            states[uf.find(index)] |= 1 << 2;

        // Check if THIS is in bottom row
        if (index > size * (size - 1))
            // Set root CONNECTED_TO_BOTTOM
            states[uf.find(index)] |= 1 << 1;

        // Check if percolates
        if ((((states[uf.find(index)] >> 1) & 1) & ((states[uf.find(index)] >> 2) & 1)) == 1)
            doesPerc = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        // Check if row and col within range 1-n
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException("row or column out of bounds");

        return (states[size * (row - 1) + col] & 1) == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // Check if row and col within range 1-n
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException("row or column out of bounds");

        int root = uf.find(size * (row - 1) + col);

        // Check roots CONNECTED_TO_TOP
        return (((states[root] >> 2) & 1) == 1);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return sitesOpened;
    }

    // does the system percolate?
    public boolean percolates() {

        return doesPerc;
    }
}
