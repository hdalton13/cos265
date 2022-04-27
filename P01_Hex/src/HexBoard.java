/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

public class HexBoard {
    private int sizeN;
    private int unSet;
    private int squareN;
    private int[] board; // answers which player is int the hex or not
    //union stuff

    //private UF uf;
    //private QuickFindUF uf;
    private WeightedQuickUnionUF uf;

    public HexBoard(int N) {
        //is the constructor that creates N-by-N grid with all tiles unset.
        if(N<0) throw new java.lang.IllegalArgumentException();
        sizeN = N;
        squareN = sizeN * sizeN;
        unSet = squareN;
        board= new int[squareN+4];
        board[squareN]=1;
        board[squareN+1]=2;
        board[squareN+2]=2;
        board[squareN+3]=1;

        //uf = new UF(squareN+4);
        //uf = new QuickFindUF(squareN+4);
        uf = new WeightedQuickUnionUF(squareN+4);

    }

    public int getPlayer(int row, int col) {
        if(row<0 || row>=sizeN || col<0 || col>=sizeN) throw new java.lang.IndexOutOfBoundsException();
        int index= row+col *sizeN;
        return board[index];
    }

    public boolean isSet(int row, int col) {
        //returns whether tile in row row and column col has been set by a player.
        if(row<0 || row>=sizeN || col<0 || col>=sizeN) throw new java.lang.IndexOutOfBoundsException();
        int index= row+col *sizeN;
        if(board[index]!=0) return true;
        return false;
    }

    public boolean isOnWinningPath(int row, int col) {
        //returns whether the tile is on the winning path.
        if(row<0 || row>=sizeN || col<0 || col>=sizeN) throw new java.lang.IndexOutOfBoundsException();

        int index= row+col *sizeN;
        if(board[index]==1){
            if(uf.connected(index,squareN) && uf.connected(index,squareN+3)) return true;
        }
        else if (board[index]==2){
            if(uf.connected(index,squareN+1) && uf.connected(index,squareN+2)) return true;
        }

        return false;
    }

    /*
    public void setTileA1(int row, int col, int player) {
        //attempt to simplify
        if(row<0 || row>=sizeN || col<0 || col>=sizeN) throw new java.lang.IndexOutOfBoundsException();
        int index= row+col *sizeN;
        if(board[index]!=0) throw new java.lang.IllegalArgumentException();
        board[index] = player;
        unSet--;


    }*/

    public void setTile(int row, int col, int player) {
    //setTile sets tile to specified player
        if(row<0 || row>=sizeN || col<0 || col>=sizeN) throw new java.lang.IndexOutOfBoundsException();
        int index= row+col *sizeN;
        if(board[index]!=0) throw new java.lang.IllegalArgumentException();
        board[index] = player;
        unSet--;
    //union ends (0 and sqrt(sizeN))
        if(index==0 ){
            if(board[index]==board[squareN]) uf.union(index, squareN);
            else if(board[index]==board[squareN+2]) uf.union(index, squareN+2);
            if(board[index]==board[index+1]) uf.union(index,(index+1));
            if(board[index]==board[index+sizeN]) uf.union(index,(index+sizeN));
        }else if(index==squareN-1){
            if(board[index]==board[squareN+1]) uf.union(index, squareN+1);
            else if(board[index]==board[squareN+3]) uf.union(index, squareN+3);
            if(board[index]==board[index-1]) uf.union((index-1), index);
            if(board[index]==board[index-sizeN]) uf.union((index-sizeN), index);
        }
    //union ends (0+sizeN && squareN-sizeN)
        else if(index==sizeN-1){ //the top of the diamond
            if(board[index]==board[squareN]) uf.union(index, squareN);
            else if(board[index]==board[squareN+1]) uf.union(index, squareN+1);
            if(board[index]==board[index-1]) uf.union(index,(index-1)); //minus one
            if(board[index]==board[index+(sizeN-1)]) uf.union(index,(index+(sizeN-1)));
            if(board[index]==board[index+sizeN]) uf.union(index,(index+sizeN));
        }
        else if(index==squareN-sizeN){ //bottom of the diamond
            if(board[index]==board[squareN+2]) uf.union(index, squareN+2);
            else if(board[index]==board[squareN+3]) uf.union(index, squareN+3);
            if(board[index]==board[index-sizeN]) uf.union(index,(index-sizeN));
            if(board[index]==board[index-(sizeN-1)]) uf.union(index,(index-(sizeN-1)));
            if(board[index]==board[index+1]) uf.union(index,(index+1)); //plus one
        }
    //union edges for 16
        else if((col == 0 && row !=0)){
            if(board[index]==board[squareN]) uf.union(index, squareN); //end check
            if(board[index]==board[index+1]) uf.union(index,(index+1)); //plus one
            if(board[index]==board[index-1]) uf.union(index,(index-1)); //minus one
            if(board[index]==board[index+(sizeN-1)]) uf.union(index,(index+(sizeN-1))); //plus three
            if(board[index]==board[index+sizeN]) uf.union(index,(index+sizeN)); //plus four
        }
    //union edges for 17
        else if((col != 0 && row ==sizeN-1)){
            if(board[index]==board[squareN+1]) uf.union(index, squareN+1); //end check
            if(board[index]==board[index-1]) uf.union(index,(index-1)); //minus one
            if(board[index]==board[index+(sizeN-1)]) uf.union(index,(index+(sizeN-1))); //plus three
            if(board[index]==board[index+sizeN]) uf.union(index,(index+sizeN)); //plus four
            if(board[index]==board[index-sizeN]) uf.union(index,(index-sizeN)); //minus 4
        }
    //union edges for 18
        else if((col != 0 && row ==0)){
            if(board[index]==board[squareN+2]) uf.union(index, squareN+2); //end check
            if(board[index]==board[index+1]) uf.union(index,(index+1)); //plus one
            if(board[index]==board[index-(sizeN-1)]) uf.union(index,(index-(sizeN-1))); //minus 3
            if(board[index]==board[index-sizeN]) uf.union(index,(index-sizeN)); //minus 4
            if(board[index]==board[index+sizeN]) uf.union(index,(index+sizeN)); //plus 4
        }
    //union edges of 19
        else if((col == sizeN-1 && row !=0)){
            if(board[index]==board[squareN+3]) uf.union(index, squareN+3); //end check
            if(board[index]==board[index+1]) uf.union(index,(index+1)); //plus one
            if(board[index]==board[index-1]) uf.union(index,(index-1)); //minus one
            if(board[index]==board[index-(sizeN-1)]) uf.union(index,(index-(sizeN-1))); //minus 3
            if(board[index]==board[index-sizeN]) uf.union(index,(index-sizeN)); //minus 4
        }
    //union middle
        else {
            if(board[index]==board[index+1]) uf.union(index,(index+1)); //plus one
            if(board[index]==board[index-1]) uf.union(index,(index-1)); //minus one
            if(board[index]==board[index+(sizeN-1)]) uf.union(index,(index+(sizeN-1))); //plus three
            if(board[index]==board[index-(sizeN-1)]) uf.union(index,(index-(sizeN-1))); //minus three
            if(board[index]==board[index+sizeN]) uf.union(index,(index+sizeN)); //plus 4
            if(board[index]==board[index-sizeN]) uf.union(index,(index-sizeN)); //minus 4
        }
    }

    public boolean hasPlayer1Won() {
        //return whether the game has been won by player 1 (respectively).
        if(uf.connected(squareN, squareN+3)) return true;
        return false;
    }

    public boolean hasPlayer2Won() {
        //return whether the game has been won by player 2 (respectively).
        if(uf.connected(squareN+1, squareN+2)) return true;
        return false;
    }

    public int numberOfUnsetTiles() {
        return unSet;
    }
}
