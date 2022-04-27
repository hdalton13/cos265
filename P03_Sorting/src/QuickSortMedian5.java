/**
 * The following code is *mostly* a copy of Quick class (quick sort) from algs4.jar
 */
/*Assignment Description:
Add median-of-5 partitioning to quicksort by implementing the median function in
QuickSortMedian5.MedianOf5 class.

Run doubling tests to determine the effectiveness of the change, in comparison both
to the standard algorithm (Quick.sort()) and to median-of-3 partitioning (previous
 exercise).

For full credit, devise a median-of-5 algorithm that uses fewer than seven compares
on any input.
*/

public class QuickSortMedian5 extends QuickSortMedian {

    public static class MedianOf5 extends MedianOfN {
        public MedianOf5() {
            // tell QuickSortMedian.MedianOfN we will find the median of 5 items
            super(5);
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
            int index3 = indices[3];
            int index4 = indices[4];
            Comparable a0 = a[index0];
            Comparable a1 = a[index1];
            Comparable a2 = a[index2];
            Comparable a3 = a[index3];
            Comparable a4 = a[index4];

            // find median in a0,a1,a2,a3,a4, and return respective index
            // if a0 is median, return i0
            // if a1 is median, return i1
            // etc.
            Comparable upper1, upper2, lower1, lower2, upper3, upper4, lower3, lower4;
            int indexUpper1, indexLower1, indexUpper2, indexLower2, indexUpper3, indexLower3, indexUpper4, indexLower4;

            int pair1= a0.compareTo(a1);
            if(pair1<0){
                upper1= a1;
                lower1= a0;
                indexUpper1= index1;
                indexLower1= index0;
            }else {
                upper1 = a0;
                lower1 = a1;
                indexUpper1= index0;
                indexLower1= index1;

            }

            int pair2= a2.compareTo(a3);
            if(pair2<0){
                upper2= a3;
                lower2= a2;
                indexUpper2= index3;
                indexLower2= index2;

            }else {
                upper2= a2;
                lower2= a3;
                indexUpper2= index2;
                indexLower2= index3;
            }

            int upperCompare= upper1.compareTo(upper2);
            if(upperCompare<0) {
                upper2=null;

                if(lower2.compareTo(a4)<0) {
                    upper4= a4;
                    lower4 = lower2;
                    indexUpper4= index4;
                    indexLower4= indexLower2;
                }else{
                    upper4= lower2;
                    lower4 = a4;
                    indexUpper4= indexLower2;
                    indexLower4= index4;
                }
                upper3=upper1;
                lower3=lower1;
                indexUpper3= indexUpper1;
                indexLower3= indexLower1;
            }
            else {
                upper1=null;
                if(lower1.compareTo(a4)>=0) {
                    upper3= lower1;
                    lower3 = a4;
                    indexUpper3= indexLower1;
                    indexLower3= index4;
                }else{
                    upper3= a4;
                    lower3 = lower1;
                    indexUpper3= index4;
                    indexLower3= indexLower1;
                }
                upper4=upper2;
                lower4=lower2;
                indexUpper4= indexUpper2;
                indexLower4= indexLower2;

            }
            if(upper3.compareTo(upper4)<0){
                upper4=null;
                if(lower4.compareTo(upper3)<0) {
                    return indexUpper3;
                }else{
                    return indexLower4;
                }

            }else{
                upper3=null;
                if(lower3.compareTo(upper4)<0) {
                    return indexUpper4;
                }else{
                    return indexLower3;
                }
            }

/*//Inefficient by works
        //A0 checks
            if(a0.compareTo(a2)<=0 && a0.compareTo(a1)<=0 && a0.compareTo(a3)>=0 && a0.compareTo(a4)>=0) return index0; //mLLHH
            if(a0.compareTo(a2)>=0 && a0.compareTo(a1)>=0 && a0.compareTo(a3)<=0 && a0.compareTo(a4)<=0) return index0; //mHHLL
            if(a0.compareTo(a2)>=0 && a0.compareTo(a1)<=0 && a0.compareTo(a3)<=0 && a0.compareTo(a4)>=0) return index0; //mHLLH
            if(a0.compareTo(a2)<=0 && a0.compareTo(a1)>=0 && a0.compareTo(a3)>=0 && a0.compareTo(a4)<=0) return index0; //mLHLH
            if(a0.compareTo(a2)>=0 && a0.compareTo(a1)<=0 && a0.compareTo(a3)>=0 && a0.compareTo(a4)<=0) return index0; //mHLHL
            if(a0.compareTo(a2)<=0 && a0.compareTo(a1)>=0 && a0.compareTo(a3)<=0 && a0.compareTo(a4)>=0) return index0; //mLHLH

        //A1 checks
            if(a1.compareTo(a0)<=0 && a1.compareTo(a2)<=0 && a1.compareTo(a3)>=0 && a1.compareTo(a4)>=0) return index1; //LmLHH
            if(a1.compareTo(a0)>=0 && a1.compareTo(a2)>=0 && a1.compareTo(a3)<=0 && a1.compareTo(a4)<=0) return index1; //HmHLL
            if(a1.compareTo(a0)>=0 && a1.compareTo(a2)<=0 && a1.compareTo(a3)<=0 && a1.compareTo(a4)>=0) return index1; //HmLLH
            if(a1.compareTo(a0)<=0 && a1.compareTo(a2)>=0 && a1.compareTo(a3)>=0 && a1.compareTo(a4)<=0) return index1; //LmHLH
            if(a1.compareTo(a0)>=0 && a1.compareTo(a2)<=0 && a1.compareTo(a3)>=0 && a1.compareTo(a4)<=0) return index1; //HmLHL
            if(a1.compareTo(a0)<=0 && a1.compareTo(a2)>=0 && a1.compareTo(a3)<=0 && a1.compareTo(a4)>=0) return index1; //LmHLH

        //A2 Checks
            if(a2.compareTo(a0)<=0 && a2.compareTo(a1)<=0 && a2.compareTo(a3)>=0 && a2.compareTo(a4)>=0) return index2; //LLmHH
            if(a2.compareTo(a0)>=0 && a2.compareTo(a1)>=0 && a2.compareTo(a3)<=0 && a2.compareTo(a4)<=0) return index2; //HHmLL
            if(a2.compareTo(a0)>=0 && a2.compareTo(a1)<=0 && a2.compareTo(a3)<=0 && a2.compareTo(a4)>=0) return index2; //HLmLH
            if(a2.compareTo(a0)<=0 && a2.compareTo(a1)>=0 && a2.compareTo(a3)>=0 && a2.compareTo(a4)<=0) return index2; //LHmLH
            if(a2.compareTo(a0)>=0 && a2.compareTo(a1)<=0 && a2.compareTo(a3)>=0 && a2.compareTo(a4)<=0) return index2; //HLmHL
            if(a2.compareTo(a0)<=0 && a2.compareTo(a1)>=0 && a2.compareTo(a3)<=0 && a2.compareTo(a4)>=0) return index2; //LHmLH

        //A3 checks
            if(a3.compareTo(a0)<=0 && a3.compareTo(a1)<=0 && a3.compareTo(a2)>=0 && a3.compareTo(a4)>=0) return index3; //LLHmH
            if(a3.compareTo(a0)>=0 && a3.compareTo(a1)>=0 && a3.compareTo(a2)<=0 && a3.compareTo(a4)<=0) return index3; //HHLmL
            if(a3.compareTo(a0)>=0 && a3.compareTo(a1)<=0 && a3.compareTo(a2)<=0 && a3.compareTo(a4)>=0) return index3; //HLLmH
            if(a3.compareTo(a0)<=0 && a3.compareTo(a1)>=0 && a3.compareTo(a2)>=0 && a3.compareTo(a4)<=0) return index3; //LHLmH
            if(a3.compareTo(a0)>=0 && a3.compareTo(a1)<=0 && a3.compareTo(a2)>=0 && a3.compareTo(a4)<=0) return index3; //HLHmL
            if(a3.compareTo(a0)<=0 && a3.compareTo(a1)>=0 && a3.compareTo(a2)<=0 && a3.compareTo(a4)>=0) return index3; //LHLmH

            return index4;
*/
            //return index4;
        }
    }

    /***********************************************************************
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     ***********************************************************************/
    public static void sort(Comparable[] a) {
        MedianOf5 median = new MedianOf5();
        QuickSortMedian.sort(a, median);
    }


    /***********************************************************************
     *  main() function
     *  Place all of your unit tests here
     *  Hint: created additional functions to help organize your tests
     ***********************************************************************/

    public static void main(String[] args) {
        Double[] a = {1.0,2.1,3.3,4.1,5.1};
        Double[] b = {4.1,5.1,3.3,1.1,2.1};
        Double[] c = {5.5,1.1,3.3,2.2,4.4};
        Double[] d = {1.1,5.5,3.3,4.1,2.1};
        Double[] e = {5.5,1.1,3.3,4.4,2.1};
        Double[] f = {2.1,5.5,3.3,1.1,4.4};

        int[] index = {0,1,2,3,4};
        QuickSortMedian5.MedianOf5 m = new QuickSortMedian5.MedianOf5();
        StdOut.println("Index Output= " + m.median(a,index));
        StdOut.println("Index Output= " + m.median(b,index));
        StdOut.println("Index Output= " + m.median(c,index));
        StdOut.println("Index Output= " + m.median(d,index));
        StdOut.println("Index Output= " + m.median(e,index));
        StdOut.println("Index Output= " + m.median(f,index));


//        Double[] a = {0.0};
//        QuickSortMedian5.sort(a);
    }
}
