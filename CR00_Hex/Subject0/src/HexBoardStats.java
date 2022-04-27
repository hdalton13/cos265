/****************************************************************************
 *  Command: HexBoardStats N0 N1 T
 *
 *  This program takes the board sizes N0,N1 and game count T as a command-line
 *  arguments. Then, the program runs T games for each board size N where
 *  N0 <= N <= N1 and where each play randomly chooses an unset tile to set in
 *  order to estimate the probability that player 1 will win.
 ****************************************************************************/

public class HexBoardStats {
    public HexBoardStats(int N0, int N1, int T) {
    }

    public int getN0() {
        return -1;
    }

    public int getN1() {
        return -1;
    }

    public int getT() {
        return -1;
    }

    public double getP1WinProbabilityEstimate(int n) {
        return -1.0;
    }

    public double getP2WinProbabilityEstimate(int n) {
        return -1.0;
    }

    public static void main(String[] args) {
    }
}
