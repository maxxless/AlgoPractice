import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    private static int swapCounter;
    private static int comparatorCounter;

    private static <K> void merge(List<K> leftArray, List<K> rightArray, List<K> array, Comparator<K> comparator) {

        int i = 0, j = 0;
        while (i + j < array.size()) {
            if (j == rightArray.size() || (i < leftArray.size() && comparator.compare(leftArray.get(i), rightArray.get(j)) < 0)) {
                array.set(i + j, leftArray.get(i++));
                comparatorCounter++;
                swapCounter++;
            } else {
                array.set(i + j, rightArray.get(j++));
                comparatorCounter++;
            }
        }
    }

    private static <K> void merge(List<K> array, Comparator<K> comparator) {
        int n = array.size();
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        List<K> leftArray = new ArrayList<>(array.subList(0, mid));
        List<K> rightArray = new ArrayList<>(array.subList(mid, n));

        merge(leftArray, comparator);
        merge(rightArray, comparator);

        merge(leftArray, rightArray, array, comparator);
    }

    public static <K> void mergeSort(List<K> array, Comparator<K> comparator) {
        swapCounter = 0;
        comparatorCounter = 0;
        long startTime = System.nanoTime();

        merge(array, comparator);

        long stopTime = System.nanoTime();
        long algoTime = stopTime - startTime;

        System.out.println("\nMerge sort:\nComparator Counter - " + comparatorCounter + "\nSwap Counter - " + swapCounter);
        System.out.println("Execution time - " + algoTime + " ns");
    }
}