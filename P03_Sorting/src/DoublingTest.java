import javax.print.attribute.standard.SheetCollate;

public class DoublingTest {
    /***********************************************************************
     *  main() function
     *  Place all of your unit tests here
     *  Hint: created additional functions to help organize your tests
     ***********************************************************************/
    //Doubling hypothesis == Slide set Analysis of Algorithms (24 & 25)

    public static double InsertionSortTime(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        Insertion.sort(a);
        return sw.elapsedTime();
    }

    public static double SelectionSortTime(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        Selection.sort(a);
        return sw.elapsedTime();
    }
    public static double BubbleTime(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        Bubble.sort(a);
        return sw.elapsedTime();
    }
    public static double ShellTime(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        Shell.sort(a);
        return sw.elapsedTime();
    }
    public static double QuickTime(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        Quick.sort(a);
        return sw.elapsedTime();
    }
    public static double QSM3Time(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        QuickSortMedian3.sort(a);
        return sw.elapsedTime();
    }
    public static double QSM5Time(int sizeN, Double[] a){
        Stopwatch sw = new Stopwatch();
        QuickSortMedian5.sort(a);
        return sw.elapsedTime();
    }


    public static void InsertionDoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (InsertionSortTime(N, RandomArray(N)) + InsertionSortTime(N, RandomArray(N)) +
                    InsertionSortTime(N, RandomArray(N)) + InsertionSortTime(N, RandomArray(N))) / 4;
            StdOut.println("Insertion Sort: " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }
    public static void SelectionDoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (SelectionSortTime(N, RandomArray(N)) + SelectionSortTime(N, RandomArray(N)) +
                    SelectionSortTime(N, RandomArray(N)) + SelectionSortTime(N, RandomArray(N))) / 4;
            StdOut.println("Selection Sort: " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }
    public static void BubbleDoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (BubbleTime(N, RandomArray(N)) + BubbleTime(N, RandomArray(N)) +
                    BubbleTime(N, RandomArray(N)) + BubbleTime(N, RandomArray(N))) / 4;
            StdOut.println("Bubble Sort: " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }
    public static void ShellDoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (ShellTime(N, RandomArray(N)) + ShellTime(N, RandomArray(N)) +
                    ShellTime(N, RandomArray(N)) + ShellTime(N, RandomArray(N))) / 4;
            StdOut.println("Shell Sort: " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }
    public static void QuickDoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (QuickTime(N, RandomArray(N)) + QuickTime(N, RandomArray(N)) +
                    QuickTime(N, RandomArray(N)) + QuickTime(N, RandomArray(N))) / 4;
            StdOut.println("Quick Sort: " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }
    public static void QSM3DoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (QSM3Time(N, RandomArray(N)) + QSM3Time(N, RandomArray(N)) +
                    QSM3Time(N, RandomArray(N)) + QSM3Time(N, RandomArray(N))) / 4;
            StdOut.println("Quick Sort Median 3 " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }
    public static void QSM5DoubleTest(int N){
        double sortingTime=0;
        for(int i=0; i<4; i++) {
            sortingTime = (QSM5Time(N, RandomArray(N)) + QSM5Time(N, RandomArray(N)) +
                    QSM5Time(N, RandomArray(N)) + QSM5Time(N, RandomArray(N))) / 4;
            StdOut.println("Quick Sort Median 5: " + "N= " + N + " Time= " + sortingTime);
            N*=2;
        }
        StdOut.println("\n");
    }

    public static Double[] RandomArray(int N){
        Double[] myDoubleArray = new Double[N];
        for (int i = 0; i < myDoubleArray.length; i++) {
            myDoubleArray[i] = Math.random() * 100;
        }
        return myDoubleArray;
    }

    public static void main(String[] args) {
        int N=5000;
        InsertionDoubleTest(N);
        SelectionDoubleTest(N);
        BubbleDoubleTest(N);
        ShellDoubleTest(N);
        QuickDoubleTest(N);
        QSM3DoubleTest(N);
        QSM5DoubleTest(N);

    }
}
