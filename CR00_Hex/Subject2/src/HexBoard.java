/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

public class HexBoard {

    private int[][] board;

    private WeightedQuickUnionUF p1UF;
    private WeightedQuickUnionUF p2UF;

//    private QuickFindUF p1UF;
//    private QuickFindUF p2UF;

    private int p1moves;
    private int p2moves;

    // Length
    public int len = 0;

    public HexBoard( int N ) {

        len = N;

        board = new int[N][N];

        // Board + 4 extra end points
         p1UF = new WeightedQuickUnionUF(( N * N ) + 4 );
         p2UF = new WeightedQuickUnionUF(( N * N ) + 4 );

//        p1UF = new QuickFindUF(( N * N ) + 4 );
//        p2UF = new QuickFindUF(( N * N ) + 4 );
    }

    public int convert( int row, int col ) {
        return row * len + col;
    }


    public int getPlayer(int row, int col) {
        return board[row][col];
    }
    
    public boolean isSet(int row, int col) {

        if( board[row][col] != 0 ) return true;

        return false;

    }

    public boolean isOnWinningPath(int row, int col) {

        int winner = 1;
        if( hasPlayer2Won() ) winner = 2;

        if( getPlayer( row, col ) != winner ) return false;

        if( winner == 1 ) { return p1UF.find( convert( row, col ) ) == p1UF.find( len ); }
        if( winner == 2 ) { return p2UF.find( convert( row, col ) ) == p2UF.find( len + 2 ); }

        return false;

    }

    public void connectTile( int player, int rowA, int colA, int rowB, int colB ) {

        if( ( rowB >= 0 && rowB < len ) && ( colB >= 0 && colB < len ) ) {

            if( player == getPlayer( rowB, colB ) ) {

                if( getPlayer( rowA, colA ) == 1 )
                    p1UF.union( convert( rowA, colA ), convert( rowB, colB ) );
                else
                    p2UF.union( convert( rowA, colA ), convert( rowB, colB ) );

            }

        }

        else if( colB == -1 && player == 1 ) p1UF.union( convert( rowA, colA ), len*len + 0 );
        else if( colB == len && player == 1 ) p1UF.union( convert( rowA, colA ), len*len + 1 );
        else if( rowB == -1 && player == 2 ) p2UF.union( convert( rowA, colA ), len*len + 2 );
        else if( rowB == len && player == 2 ) p2UF.union( convert( rowA, colA ), len*len + 3 );

    }

    public void setTile(int row, int col, int player) {

        board[row][col] = player;

        if( player == 1 ) p1moves++;
        else if( player == 2 ) p2moves++;

        connectTile( player, row, col, row - 1, col );
        connectTile( player, row, col, row - 1, col + 1 );
        connectTile( player, row, col, row, col + 1 );
        connectTile( player, row, col, row + 1, col );
        connectTile( player, row, col, row + 1, col - 1 );
        connectTile( player, row, col, row, col - 1 );

    }

    public boolean hasPlayer1Won() {

        if( p1moves < len ) return false;

        return p1UF.connected( len * len, len * len + 1);

    }

    public boolean hasPlayer2Won() {

        if( p2moves < len ) return false;

        return p2UF.connected( len * len + 2, len * len + 3);

    }

    public int numberOfUnsetTiles() {

        return ( len * len ) - p1moves - p2moves;

    }
}
