/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

public class HexBoard {
    private int[][] rowcol;
    private int[][] ufind;
    private WeightedQuickUnionUF my_union;
    private int num;
    private int my_N;
    public HexBoard(int N) {
        if (N<=0) throw new IllegalArgumentException("Invalid N");
        rowcol = new int[N+2][N+2];
        ufind = new int[N+2][N+2];
        num = N*N;
        my_N = N;
        int size = (N+2)*(N+2)+4;
        my_union = new WeightedQuickUnionUF(size);
        for(int i=0; i<N+2; i++){
            for(int j=0; j<N+2; j++){
                if (i==0){
                    rowcol[i][j]=2;
                }else if (i==N+1){
                    rowcol[i][j]=2;
                }else if (j==0){
                    rowcol[i][j]=1;
                }else if (j==N+1){
                    rowcol[i][j]=1;
                } else {
                    rowcol[i][j]=0; //0 for not taken, 1 for red, 2 for blue
                }
            }
        }
        for(int i=0; i<N+2; i++) {
            for (int j = 0; j < N+2; j++) {
                ufind[i][j] = i + j * (N + 2) + 4; //initializes unique numbers for board placements
                if(i==0) {
                    my_union.union(ufind[i][j], 0);
                }
                else if(i==(N+1)){
                    my_union.union(ufind[i][j],1);
                }
                else if(j==0){
                    my_union.union(ufind[i][j], 2);
                }
                else if(j==(N+1)){
                    my_union.union(ufind[i][j],3);
                }
            }
        }

    }

    public int getPlayer(int row, int col) {
        return rowcol[row+1][col+1];
    }
    
    public boolean isSet(int row, int col) {
        return !(rowcol[row+1][col+1] == 0);
    }

    public boolean isOnWinningPath(int row, int col) {
        //if player 1 has won, what is connected to 0 and 1 but doesnt include the extras, excluding 1st&last row&col or dead paths
        //how do i trace back to find the path that led to the win without the backwash problem?
        //need to check if can use hasPlayer1won functions?
        if((my_union.connected(ufind[row+1][col+1],2))&&(my_union.connected(ufind[row+1][col+1],3))){
            return(rowcol[row+1][col+1]==1);
        }else if((my_union.connected(ufind[row+1][col+1],0))&&(my_union.connected(ufind[row+1][col+1],1))){
            return(rowcol[row+1][col+1]==2);
        }
        return false;
    }

    public void setTile(int row, int col, int player) {
        if(row<0|col<0) throw new IndexOutOfBoundsException("row or col too small");
        if(col>=(my_N)|row>=(my_N)) throw new IndexOutOfBoundsException("row or col too big");
        int row_fix = row+1;
        int col_fix = col+1;
        if(!(rowcol[row_fix][col_fix]==0)) throw new IllegalArgumentException("tile already taken");
        rowcol[row_fix][col_fix] = player;
        num--;
        if (player == rowcol[row_fix+1][col_fix]) {
            my_union.union(ufind[row_fix][col_fix], ufind[row_fix+1][col_fix]);
        }
        if (player == rowcol[row_fix][col_fix+1]) {
            my_union.union(ufind[row_fix][col_fix], ufind[row_fix][col_fix+1]);
        }
        if (player == rowcol[row_fix-1][col_fix]) {
            my_union.union(ufind[row_fix][col_fix], ufind[row_fix-1][col_fix]);
        }
        if (player == rowcol[row_fix][col_fix-1]) {
            my_union.union(ufind[row_fix][col_fix], ufind[row_fix][col_fix-1]);
        }
        if (player == rowcol[row_fix+1][col_fix-1]) {
            my_union.union(ufind[row_fix][col_fix], ufind[row_fix+1][col_fix-1]);
        }
        if (player == rowcol[row_fix-1][col_fix+1]) {
            my_union.union(ufind[row_fix][col_fix], ufind[row_fix-1][col_fix+1]);
        }
    }

    public boolean hasPlayer1Won() { return (my_union.connected(2,3)); }

    public boolean hasPlayer2Won() { return (my_union.connected(0,1)); }

    public int numberOfUnsetTiles() {
        return num;
    }
}
