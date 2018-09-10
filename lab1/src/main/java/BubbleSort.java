import java.util.ArrayList;

public class BubbleSort {

    public static void bubbleSortInDescOrder(ArrayList<TouristVoucher> a) {
        int left = 0;
        int right = a.size() - 1;
        int swapCounter = 0;
        int comparatorCounter = 0;
        long startTime = System.nanoTime();

        for (int i = right; i > 1; i--) {
            for (int j = left; j < i; j++) {
                comparatorCounter++;
                if (a.get(j).compareTo(a.get(j + 1)) < 0) {
                    swapCounter++;
                    swap(a, j, j + 1);
                }
            }
        }
        long stopTime = System.nanoTime();
        long algoTime = stopTime - startTime;
        System.out.println("\nBubble sort:\nComparator Counter - " + comparatorCounter + "\nSwap Counter - " + swapCounter);
        System.out.println("Execution time - " + algoTime + " ns");
    }

    private static void swap(ArrayList<TouristVoucher> a, int left, int right) {
        TouristVoucher temp = a.get(left);
        a.set(left, a.get(right));
        a.set(right, temp);
    }

}