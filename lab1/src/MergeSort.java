import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {

    private static int swapCounter;
    private static int comparatorCounter;

    private static <K> void merge(K[] array1, K[] array2, K[] array, Comparator<K> comparator) {

        int i = 0, j = 0;
        while (i + j < array.length) {
            if (j == array2.length || (i < array1.length && comparator.compare(array1[i], array2[j]) < 0)) {
                array[i + j] = array1[i++];
                comparatorCounter++;
                swapCounter++;
            } else {
                array[i + j] = array2[j++];
                comparatorCounter++;
            }
        }
    }

    private static <K> void mergeSort(K[] array, Comparator<K> comparator) {
        int n = array.length;
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        K[] array1 = Arrays.copyOfRange(array, 0, mid);
        K[] array2 = Arrays.copyOfRange(array, mid, n);

        mergeSort(array1, comparator);
        mergeSort(array2, comparator);

        merge(array1, array2, array, comparator);
    }

    public static <K> void finalMergeSort(K[] array, Comparator<K> comparator) {
        long startTime = System.nanoTime();

        mergeSort(array, comparator);

        long stopTime = System.nanoTime();
        long algoTime = stopTime - startTime;
        System.out.println("\nMerge sort:\nComparator Counter - " + comparatorCounter + "\nSwap Counter - " + swapCounter);
        System.out.println("Execution time - " + algoTime + " ns");
    }
}