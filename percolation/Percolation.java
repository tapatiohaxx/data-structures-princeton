import java.util.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation{
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF backwash;
    private boolean[] openSites;
    private final int SIZE, TOP_INDEX, BOTTOM_INDEX;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int size){
        int numNodes = size*size + 2;
        union = new WeightedQuickUnionUF(numNodes);
        backwash = new WeightedQuickUnionUF(numNodes);
        openSites = new boolean[size*size];
        SIZE = size;
        TOP_INDEX = size * size;
        BOTTOM_INDEX = size * size + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row < 1 || row > SIZE || col < 1 || col > SIZE)
            throw new IndexOutOfBoundsException();
        
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > SIZE || col < 1 || col > SIZE)
            throw new IndexOutOfBoundsException();
        if(openSites[row*col] == true)
            return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int closed = 0;
        for(int i =1; i<openSites.length;  ++i)
           if( openSites[i] == false)
                ++closed;
        
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return 1;
    }

    // does the system percolate?
    public boolean percolates(){
        return true;
    }

    // test client (optional)
    public static void main(String[] args){
        System.out.println("hello world");
    }
}
