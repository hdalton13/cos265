import java.util.Arrays;

public class BruteForceSearch {
    public static int rank(int key, int[] a) {
        // returns the index position of key in a if it exists
        // else, returns -1
        for(int i =0; i < a.length; i++){
            if(a[i] == key) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        // read ints from args[0] and store in whitelist
        // read ints from args[1] and store in checklist
        // print each int that is in checklist but is not in whitelist
        Stopwatch sw = new Stopwatch();
        //do stuff here


        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        //Arrays.sort(whitelist);

        In test = new In(args[1]);
        while(!test.isEmpty()){
            int t = test.readInt();
            if(rank(t, whitelist)<0){
                StdOut.println(t);
            }
        }
        StdOut.println("Elapsed Time:" + sw.elapsedTime());
    }
}
