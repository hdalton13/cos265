import java.util.Random;

public class EdgeCases {
    /***********************************************************************
     *  main() function
     *  Place all of your unit tests here
     *  Hint: created additional functions to help organize your tests
     **********************************************************************
     * @return*/
/*Assignment Description:
Write a client that runs sort() on difficult or pathological cases that might
turn up in practical applications.
    - Examples include arrays that are already in order
    - arrays in reverse order
    - arrays where all keys are the same
    - arrays consisting of only two distinct values
    - arrays of size 0 or 1.
Use standard bubble sort, selection sort, insertion sort, shellsort, quicksort.

Hint: you may want to write functions that take a size
and generate a pathological arrays.
*/
    public static void BubbleTest(int N){
        //Bubble.sort(sizeOne_Zero(N));
        Bubble.sort(isOrdered(N));
//        Bubble.sort(isReverseOrdered(N));
//        Bubble.sort(isSameKey(N));
//        Bubble.sort(onlyTwoDistinctElements(N));

    }
    public static void SelectionTest(int N){
        //Selection.sort(sizeOne_Zero(N));
        Selection.sort(isOrdered(N));
//        Selection.sort(isReverseOrdered(N));
//        Selection.sort(isSameKey(N));
//        Selection.sort(onlyTwoDistinctElements(N));
    }
    public static void InsertionTest(int N){
        //Insertion.sort(sizeOne_Zero(N));
        Insertion.sort(isOrdered(N));
//        Insertion.sort(isReverseOrdered(N));
//        Insertion.sort(isSameKey(N));
//        Insertion.sort(onlyTwoDistinctElements(N));
    }
    public static void ShellTest(int N){
       // Shell.sort(sizeOne_Zero(N));
        Shell.sort(isOrdered(N));
//        Shell.sort(isReverseOrdered(N));
//        Shell.sort(isSameKey(N));
//        Shell.sort(onlyTwoDistinctElements(N));
    }
    public static void QuickTest(int N){
//        Quick.sort(sizeOne_Zero(N));
        Quick.sort(isOrdered(N));
//        Quick.sort(isReverseOrdered(N));
//        Quick.sort(isSameKey(N));
//        Quick.sort(onlyTwoDistinctElements(N));
    }

    public static Double[] sizeOne_Zero(int size){
        //if (arrayList.length<=1) return true;
        Double [] sizeOne = new Double[size];
        return sizeOne;
    }

    public static Double[] isOrdered(int size){
        /* int orderedCount =0;
        for (int i=0; i< arrayList.length-1; i++){
            if ((double)arrayList[i] < (double)arrayList[i+1]) orderedCount++;
        }
        return orderedCount == arrayList.length-1;*/
        Double [] orderedList = new Double[size];
        for (int i=0; i<size; i++){
            orderedList[i]=(double)i+1;
        }
        return orderedList;
    }

    public static Double[] isReverseOrdered(int size){
        /*int reverseOC =0;
        for (int i=0; i< arrayList.length-1; i++){
            if ((double)arrayList[i] > (double)arrayList[i+1]) reverseOC++;
        }
        return reverseOC == arrayList.length-1;*/
        Double [] reveredOrderedList = new Double[size];
        double reversed=size;
        for(int i=0; i<size; i--){
            reveredOrderedList[i]=reversed;
            reversed--;
        }
        return reveredOrderedList;
    }

    public static Double[] isSameKey(int size){
        /*int sameKC =0;
        for (int i=0; i< arrayList.length-1; i++){
            if ((double)arrayList[i] == (double)arrayList[i+1]) {
                sameKC++;
            }
        }
        return sameKC == arrayList.length-1;*/
        Double [] sameKey = new Double[size];
        Random generator = new Random();
        int min=1; int max=100;
        double randomNum= min + generator.nextInt(max-min+1);
        for(int i=0; i<size; i++){
            sameKey[i]=randomNum;
        }
        return sameKey;
    }

    public static Double[] onlyTwoDistinctElements(int size){
        Double [] twoDistinctElements = new Double[size];
        int min=0; int max=100;
        Random generator= new Random();
        double randomNum1= min + generator.nextInt(max-min+1);
        double randomNum2= min + generator.nextInt(max-min+1);
        for (int i=0; i<size; i++){
            if( randomNum1 == randomNum2 ){
                randomNum2 = min + generator.nextInt(max-min+1);
            }
            if (i%2==0){
                twoDistinctElements[i] = randomNum1;
            }else{
                twoDistinctElements[i] = randomNum2;
            }
        }
        return twoDistinctElements;
    }

    public static void main(String[] args) {
        int size =10;
        BubbleTest(size);
        SelectionTest(size);
        InsertionTest(size);
        ShellTest(size);
        QuickTest(size);

    }
}


/*//Double[] a = { 4.4, 4.4, 4.4, 4.4 };
        //Double[] a = { 1.1, 2.2, 3.3, 4.4 };
        Double[] a = { 4.4, 3.3, 2.2, 1.1 };
        //Double[] a = { 4.4, 7.7, 2.2, 5.5 };
        //Double[] a = { 4.4, 4.4, 3.3, 3.3 };

        StdOut.println(sizeOne_Zero(a));
        StdOut.println(isOrdered(a));
        StdOut.println(isReverseOrdered(a));
        StdOut.println(isSameKey(a));
        StdOut.println(onlyTwoDistinctElements(a));



        Bubble.sort(a);     // bubble sort
                Selection.sort(a);  // selection sort
                Insertion.sort(a);  // insertion sort
                Shell.sort(a);      // Shellsort
                Quick.sort(a);      // quicksort
                }
*/
