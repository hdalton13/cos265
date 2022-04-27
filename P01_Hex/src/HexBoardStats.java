/****************************************************************************
 *  Command: HexBoardStats N0 N1 T
 *
 *  This program takes the board sizes N0,N1 and game count T as a command-line
 *  arguments. Then, the program runs T games for each board size N where
 *  N0 <= N <= N1 and where each play randomly chooses an unset tile to set in
 *  order to estimate the probability that player 1 will win.
 ****************************************************************************/

public class HexBoardStats {
    private int N0; // lowest board size
    private int N1; // highest board size
    private int T; // number of games

    private int[] boardGameWon; //array index= size of board; //boardGameWon[index]= number of time player one WON


    private HexBoard statHex;

    public HexBoardStats(int pN0, int pN1, int pT) {
        this.N0 = pN0;
        this.N1= pN1;
        this.T= pT;

        boardGameWon = new int[N1];

        for(int N = this.N0; N <= this.N1; N++){
            int p1W=0;
            Stopwatch sw = new Stopwatch();
            for(int n = 0; n < this.T; n++){
                statHex= new HexBoard(N);
                int player = 1;
                while (!statHex.hasPlayer1Won() && !statHex.hasPlayer2Won()) {
                    int r = StdRandom.uniform(0, N);
                    int c = StdRandom.uniform(0, N);

                    // is board tile already set?
                    if (!statHex.isSet(r,c)) {
                        //StdOut.println(r + " " + c + " " + player);
                        // set board tile
                        statHex.setTile(r, c, player);

                        // switch player
                        player = (player==1) ? 2 : 1;
                    }

                } //while closing parameter
                if (statHex.hasPlayer1Won()) p1W++;
            } //inner for loop
            StdOut.println("Time passed " + sw.elapsedTime());
            int p2W= this.T - boardGameWon[N-N0];
            //StdOut.println("N = " + N + " P1= " + boardGameWon[N - N0] + " P2= " + p2W + " Time " + boardGameTime[N-N0]);
            boardGameWon[N-N0] = p1W;
        } //outer for loop
    }

    public int getN0() {
        return N0;
    }

    public int getN1() {
        return N1;
    }

    public int getT() {
        return T;
    }

    public double getP1WinProbabilityEstimate(int n) {
        if(n<N0 || n>N1) throw new java.lang.IndexOutOfBoundsException();
        double win1= boardGameWon[n-N0];
        return (win1/this.T)*100;
    }

    public double getP2WinProbabilityEstimate(int n) {
        if(n<N0 || n>N1) throw new java.lang.IndexOutOfBoundsException();
        double win2= T - boardGameWon[n-N0];
        return (win2/this.T)*100;
    }

    public static void main(String[] args) {

        int n0 = 2;
        int n1 = 2;
        int t = 100000;

        HexBoardStats stat;
        StdOut.println("T = " + t);
        stat = new HexBoardStats(n0, n1, t);

        for (int N = n0; N <= n1; N++) {
            StdOut.println("size= "+ N  + " Prob 1 = " + stat.getP1WinProbabilityEstimate(N) + " Prob 2 = " + stat.getP2WinProbabilityEstimate(N));



            //}


        }
    }
}
