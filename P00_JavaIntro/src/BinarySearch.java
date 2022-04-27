import java.util.Arrays;
public class BinarySearch {
    public static int rank(int key, int[] a) {
        // returns the index position of key in a if it exists
        // else, returns -1
        int lo=0;
        int hi=a.length-1;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            //StdOut.println(mid);
            if (key < a[mid]) hi = mid-1;
            else if (key > a[mid]) lo = mid+1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        // read ints from args[0] and store in whitelist
        // read ints from args[1] and store in checklist
        // print each int that is in checklist but is not in whitelist
        Stopwatch sw = new Stopwatch();

        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        In test = new In(args[1]);
        while(!test.isEmpty()){
            int t = test.readInt();
            //StdOut.println(t);
            if(rank(t, whitelist)<0){
                StdOut.println(t);
            }
        }
        StdOut.println("Elapsed Time:" + sw.elapsedTime());
    }
}
