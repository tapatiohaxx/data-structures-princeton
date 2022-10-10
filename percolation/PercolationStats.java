import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private double[] stats;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        assertPositive(n);
        assertPositive(trials);
        stats = new double[trials];
        runExperiments(n, trials);
    }

    private void assertPositive(int num) {
        if (num < 1) {
            throw new IllegalArgumentException();
        }
    }

    private void runExperiments(int size, int trials) {
        for (int i = 0; i < trials; i++)
            stats[i] = runExperiment(size);
    }
    private double runExperiment(int size) {
        Percolation p = new Percolation(size);
        int openSpaces = 0;
        do {
            int row = random(size);
            int col = random(size);
            if (!p.isOpen(row, col)){
                p.open(row, col);
                openSpaces++;
            }
        } while (!p.percolates());

        return (double) openSpaces/((double) size*size);
    }

    private int random(int size) {
        return StdRandom.uniformInt(1, size + 1);
    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - confidence();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + confidence();
    }
    private double confidence() {
        return (1.96 * stddev() / Math.sqrt(stats.length));
    }
   // test client (see below)
   public static void main(String[] args){
    PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
   }

}
