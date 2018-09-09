import java.io.FileReader;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static class PriceComparator implements Comparator<TouristVoucher> {
        public int compare(TouristVoucher a, TouristVoucher b) {
            return a.comparePriceTo(b);
        }
    }

    private static <T> void printArray(T[] a) {
        for (T t : a) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        int i = 0;
        TouristVoucher[] touristVouchers = new TouristVoucher[5];
        Scanner scanner;
        try {
            String filePath = "D:\\Кєк\\структури даних і алго\\AlgoPractice\\lab1\\vouchers.txt";
            scanner = new Scanner(new FileReader(filePath));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                touristVouchers[i] = new TouristVoucher(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Initial array:");
        printArray(touristVouchers);
        BubbleSort.bubbleSortInDescOrder(touristVouchers);
        System.out.println("\nArray with the bubble sorting performed (descending by the voucher's duration):");
        printArray(touristVouchers);

        MergeSort.finalMergeSort(touristVouchers, new PriceComparator());
        System.out.println("\nArray with the merge sorting performed (ascending by the voucher's price):");
        printArray(touristVouchers);
    }
}
