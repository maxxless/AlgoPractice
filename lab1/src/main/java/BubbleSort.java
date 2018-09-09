public class BubbleSort {

    public static <T extends Comparable<T>> void bubbleSortInDescOrder(T[] a) {
        int left = 0;
        int right = a.length - 1;
        int swapCounter = 0;
        int comparatorCounter = 0;
        long startTime = System.nanoTime();

        for (int i = right; i > 1; i--) {
            for (int j = left; j < i; j++) {
                comparatorCounter++;
                if (a[j].compareTo(a[j + 1]) < 0) {
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

    private static void swap(Object[] a, int left, int right) {
        Object temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

}