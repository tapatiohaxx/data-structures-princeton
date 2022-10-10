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
        checkBounds(row, col);
        openSites[makeOneDimensional(row, col)] = true;
        connectToVirtualTopNode(row, col);
        connectToAdjacents(row, col);
        connectToVirtualBottomNode(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        checkBounds(row, col);
        return openSites[makeOneDimensional(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        checkBounds(row, col);
        return union.connected(makeOneDimensional(row, col), TOP_INDEX);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return 1;
    }

    // does the system percolate?
    public boolean percolates(){
        return backwash.connected(BOTTOM_INDEX, TOP_INDEX);
    }


    private void checkBounds(int row, int col){
        if (row < 1 || row > SIZE || col < 1 || col > SIZE)
            throw new IndexOutOfBoundsException();             
    }
    private void connectToVirtualTopNode(int row, int col) {
        if (row == 1) {
            union(TOP_INDEX, makeOneDimensional(row, col));
        }
    }
    private int makeOneDimensional(int row, int col){
        return (SIZE *(row-1) + (col -1 ) );
    }
    private void connectToAdjacents(int row, int col) {
        connectTopNode(row, col);
        connectBottomNode(row, col);
        connectLeftNode(row, col);
        connectRightNode(row, col);
    }

    private void connectTopNode(int row, int col) {
        if (row > 1 && isOpen(row - 1, col)) 
            union(makeOneDimensional(row - 1, col), makeOneDimensional(row, col));
    }

    private void connectBottomNode(int row, int col) {
        if (row < SIZE && isOpen(row + 1, col))
            union(makeOneDimensional(row + 1, col), makeOneDimensional(row, col));
    }

    private void connectLeftNode(int row, int col) {
        if (col > 1 && isOpen(row, col - 1))
            union(makeOneDimensional(row, col - 1), makeOneDimensional(row, col));
    }

    private void connectRightNode(int row, int col) {
        if (col < SIZE && isOpen(row, col + 1))
            union(makeOneDimensional(row, col + 1), makeOneDimensional(row, col));
    }

    private void connectToVirtualBottomNode(int row, int col) {
        if (row == SIZE)
            backwash.union(BOTTOM_INDEX, makeOneDimensional(row, col));
    }
    private void union(int row, int col) {
        union.union(row, col);
        backwash.union(row, col);
    }
    // test client (optional)
    public static void main(String[] args){
        System.out.println("hello world");
    }
}
