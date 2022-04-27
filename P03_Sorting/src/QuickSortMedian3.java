/**
 * The following code is *mostly* a copy of Quick class (quick sort) from algs4.jar
 */

public class QuickSortMedian3 extends QuickSortMedian {

    public static class MedianOf3 extends MedianOfN {
        public MedianOf3() {
            // tell QuickSortMedian.MedianOfN we will find the median of 3 items
            super(3);
        }

        /***********************************************************
         * Determines which index in parameter indices points to
         * the median value in parameter a
         * @param a the array containing values
         * @param indices the array containing indices into a
         * @return the index of median value
         ***********************************************************/
        public int median(Comparable[] a, int[] indices) {
            // get values at specified indices
            int index0 = indices[0];
            int index1 = indices[1];
            int index2 = indices[2];
            Comparable a0 = a[index0];
            Comparable a1 = a[index1];
            Comparable a2 = a[index2];

//            StdOut.println("a0= " +a0);
//            StdOut.println("a1= "+a1);
//            StdOut.println("a2= "+a2);
    //A0 checks
            if((a0.compareTo(a1)<0 && a0.compareTo(a2)>0)) return index0;  //a0 < a1 && a0 > a2
            if ((a0.compareTo(a2)<0 && a0.compareTo(a1)>0)) return index0; //a0 > a1 && a0 < a2
    //A1 checks
            if((a1.compareTo(a2)<0 && a1.compareTo(a0)>0)) return index1; //a1 > a0 && a1 < a2
            if((a1.compareTo(a0)<0 && a1.compareTo(a2)>0)) return index1; //a1 < a0 && a1 > a2

    //A2 Checks
            if((a2.compareTo(a1)<0 && a2.compareTo(a0)>0)) return index2;
            if((a2.compareTo(a0)<0 && a2.compareTo(a1)>0)) return index2;



            // find median in a0,a1,a2, and return respective index
            // if a0 is median, return index0
            // if a1 is median, return index1
            // else a2 is median, return index2

            return index2;
        }
    }


    /***********************************************************************
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     ***********************************************************************/
    public static void sort(Comparable[] a) {
        MedianOf3 median = new MedianOf3();
        sort(a, median);
    }

    /***********************************************************************
     *  main() function
     *  Place all of your unit tests here
     *  Hint: created additional functions to help organize your tests
     ***********************************************************************/

    public static void main(String[] args) {
//
//        QuickSortMedian3.sort(a);
        Double[] a = {1.0,2.1,3.1}; //return 1
        Double[] b = {0.0,2.1,1.1}; //return 2
        Double[] c = {2.1,1.1,0.0}; //return 1
        Double[] d = {1.1,2.1,0.0}; //return 0
        Double[] e = {1.1,0.0,2.1}; //return 0
        Double[] f = {0.0,2.1,1.1}; //return 2

        int[] index = {0,1,2};
        MedianOf3 m = new MedianOf3();
        StdOut.println("Index Output= " + m.median(a,index));
        StdOut.println("Index Output= " + m.median(b,index));
        StdOut.println("Index Output= " + m.median(c,index));
        StdOut.println("Index Output= " + m.median(d,index));
        StdOut.println("Index Output= " + m.median(e,index));
        StdOut.println("Index Output= " + m.median(f,index));
    }
}
