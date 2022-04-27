/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

import java.lang.*;

public class HexBoard {

    String[][] b;
    public static QuickUnionUF u;
    public static QuickUnionUF w;
    int s;
    String lastSet = "";
    final int[] RLIST = {-1, -1, 0, 1, 1, 0};
    final int[] CLIST = {0, 1, 1, 0, -1, -1};
    int rw = 0;
    int cl = 0;

    public HexBoard(int N) {
        if(N>0) {
            u = new QuickUnionUF((N*N) + 4);
            w = new QuickUnionUF(N*N);
            b = new String[N][N];
            s = N;
            int c = 0;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    b[i][j] = String.valueOf(c);
                    c++;
                    //System.out.print(b[i][j] + " ");
                }
                //System.out.println();
            }
        }else{
            throw new IllegalArgumentException("You must pass a number greater than 0");
        }
    }

    public int getPlayer(int row, int col) {
        if(row>=0 && row<s && col>=0 && col<s) {
            if (b[row][col].contains("-")) {
                return 1;
            } else if (b[row][col].contains("+")) {
                return 2;
            }
        }
        return 0;
    }
    
    public boolean isSet(int row, int col) {
        checkRange(row, 0, s);
        checkRange(col, 0, s);
        return getPlayer(row, col) != 0;
    }

    public boolean isOnWinningPath(int row, int col) {
        checkRange(row, 0, s);
        checkRange(col, 0, s);
        return (w.connected(Math.abs(Integer.parseInt(b[row][col])), Math.abs(Integer.parseInt(lastSet))));
    }

    public void setTile(int row, int col, int player) {
        checkRange(row, 0, s);
        checkRange(col, 0, s);
        checkRange(player, 1, 2);
        if(!isSet(row, col)) {
            String c = "";
            String n = b[row][col];
            if (player == 1) {
                c = "-";
            } else if (player == 2) {
                c = "+";
            }
            b[row][col] = c + n;
            System.out.println(b[row][col]);
            lastSet = b[row][col];

            switch (player) {
                case 1:
                    if (col == 0) {
                        u.union(Math.abs(Integer.parseInt(b[row][col])), s * s);
                        System.out.println("Unioned (" + row + "," + col + ") to NW: " + u.connected(Math.abs(Integer.parseInt(b[row][col])), s * s));
                        System.out.println("Parent: " + u.find(Math.abs(Integer.parseInt(b[row][col]))));
                    }
                    if (col == s - 1) {
                        u.union(Math.abs(Integer.parseInt(b[row][col])), (s * s) + 1);
                        System.out.println("Unioned (" + row + "," + col + ") to SE: " + u.connected(Math.abs(Integer.parseInt(b[row][col])), (s * s) + 1));
                        System.out.println("Parent: " + u.find(Math.abs(Integer.parseInt(b[row][col]))));
                    }
                    break;
                case 2:
                    if (row == 0) {
                        u.union(Math.abs(Integer.parseInt(b[row][col])), (s * s) + 2);
                        System.out.println("Unioned (" + row + "," + col + ") to SW: " + u.connected(Math.abs(Integer.parseInt(b[row][col])), (s * s) + 2));
                        System.out.println("Parent: " + u.find(Math.abs(Integer.parseInt(b[row][col]))));
                    }
                    if (row == s - 1) {
                        u.union(Math.abs(Integer.parseInt(b[row][col])), (s * s) + 3);
                        System.out.println("Unioned (" + row + "," + col + ") to NE: " + u.connected(Math.abs(Integer.parseInt(b[row][col])), (s * s) + 3));
                        System.out.println("Parent: " + u.find(Math.abs(Integer.parseInt(b[row][col]))));
                    }
                    break;
            }

            for (int i = 0; i < 6; i++) {
                rw = row + RLIST[i];
                cl = col + CLIST[i];
                if (getPlayer(rw, cl) == player) {
                    u.union(Math.abs(Integer.parseInt(b[row][col])), Math.abs(Integer.parseInt(b[rw][cl])));
                    w.union(Math.abs(Integer.parseInt(b[row][col])), Math.abs(Integer.parseInt(b[rw][cl])));
                    System.out.println("Unioned (" + row + "," + col + ") to (" + rw + "," + cl + "): " + u.connected(Math.abs(Integer.parseInt(b[row][col])), Math.abs(Integer.parseInt(b[rw][cl]))));
                    System.out.println("Parent: " + u.find(Math.abs(Integer.parseInt(b[row][col]))));
                }
            }
        }else{
            throw new IllegalArgumentException("Tile is already set");
        }
    }

    public boolean hasPlayer1Won() {
        return u.connected((s*s), (s*s)+1);
    }

    public boolean hasPlayer2Won() {
        return u.connected((s*s)+2, (s*s)+3);
    }

    public int numberOfUnsetTiles() {
        int c = 0;
        int l = b[0].length;
        for(int i=0; i<l; i++) {
            for(int j=0; j<l; j++) {
                if(!isSet(i,j)) {
                    c++;
                }
                //System.out.print(b[i][j] + " ");
            }
            //System.out.println();
        }
        return c;
    }

    public void checkRange(int i, int min, int max) {
        if (i > max || i < min) {
            throw new IndexOutOfBoundsException(i + " is not between " + min + " and " + max);
        }
    }
}
