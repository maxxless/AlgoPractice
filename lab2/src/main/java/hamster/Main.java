package hamster;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int allFood = 0;
    private static int hamstersAmount = 0;
    private static List<Hamster> hamsters = new ArrayList<>();
    private static int currentFood = 0;
    private static int currentGreed = 0;

    public static void main(String[] args) {
        openFile();

        System.out.println("Food available " + allFood);
        System.out.println("Hamsters available " + hamstersAmount);
        System.out.println("Initial array:");

        for (Hamster hamster : hamsters) {
            System.out.println(hamster);
        }

        int result = searchingForHamster(hamsters, hamstersAmount, allFood);

        mergeSort(hamsters, 0, hamsters.size() - 1);
        System.out.println("\nSorted array:");

        for (Hamster hamster : hamsters) {
            System.out.println(hamster);
        }

        System.out.println("\nResult = " + result);
    }

    private static void openFile() {
        try {
            Scanner scanner = new Scanner(new FileReader("hamstr1.in"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();


                if (line.length() > 4) {
                    String[] hamsterFields = line.split("--");
                    hamsters.add(new Hamster(Integer.parseInt(hamsterFields[0]), Integer.parseInt(hamsterFields[1])));
                } else {
                    String[] fields = line.split(",");
                    allFood = Integer.parseInt(fields[0]);
                    hamstersAmount = Integer.parseInt(fields[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int searchingForHamster(List<Hamster> hamsters, int hamstersAmount, int allFood) {
        int maxAmount = hamstersAmount - 1;
        int lastBiggestIndex;

        for (int i = 0; i < hamstersAmount; i++) {
            currentFood += hamsters.get(i).getFoodNeeded() + hamsters.get(i).getGreed() * maxAmount;
            currentGreed += hamsters.get(i).getGreed();
        }
        while (currentFood > allFood) {
            currentFood -= currentGreed;
            lastBiggestIndex = count(hamsters, maxAmount);
            currentFood -= (hamsters.get(lastBiggestIndex).getFoodNeeded() + hamsters.get(lastBiggestIndex).getGreed() * (maxAmount - 1));
            currentGreed -= hamsters.get(lastBiggestIndex).getGreed();

            Hamster temp = hamsters.get(lastBiggestIndex);
            hamsters.set(lastBiggestIndex, hamsters.get(maxAmount));
            hamsters.set(maxAmount, temp);
            maxAmount -= 1;
        }

        return maxAmount;
    }

    private static int count(List<Hamster> hamsters, int maxAmount) {
        int conditionalIndex = 0;
        for (int i = 0; i < maxAmount; i++) {
            if ((hamsters.get(conditionalIndex).getFoodNeeded() + hamsters.get(conditionalIndex).getGreed() * maxAmount) < (hamsters.get(i).getFoodNeeded() + hamsters.get(i).getGreed() * maxAmount)) {
                conditionalIndex = i;
            }
        }
        return conditionalIndex;
    }

//    private static void sortBubble(List<Hamster> hamsters) {
//        Hamster temp;
//        for (int i = 0; i < hamsters.size() - 1; i++) {
//            for (int j = 0; j < hamsters.size() - i - 1; j++) {
//                if (hamsters.get(j).getGreed() < hamsters.get(j + 1).getGreed()) {
//                    temp = hamsters.get(j);
//                    hamsters.set(j, hamsters.get(j + 1));
//                    hamsters.set(j + 1, temp);
//                }
//            }
//        }
//    }

    private static void merge(List<Hamster> hamsters, int leftIndex, int midIndex, int rightIndex) {
        int n1 = midIndex - leftIndex + 1;
        int n2 = rightIndex - midIndex;

        List<Hamster> leftArray = new ArrayList<>();
        List<Hamster> rightArray = new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            leftArray.add(i, hamsters.get(leftIndex + i));
        for (int j = 0; j < n2; ++j)
            rightArray.add(j, hamsters.get(midIndex + 1 + j));

        int i = 0, j = 0;
        int k = leftIndex;

        searchingForHamster(leftArray, n1, allFood);
        if (searchingForHamster(leftArray, n1, allFood) == leftArray.size()) {
            searchingForHamster(rightArray, n2, allFood);
        }

        while (i < n1 && j < n2) {
            if (leftArray.get(i).getGreed() <= rightArray.get(j).getGreed()) {
                hamsters.set(k, leftArray.get(i));
                i++;
            } else {
                hamsters.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            hamsters.set(k, leftArray.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            hamsters.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    private static void mergeSort(List<Hamster> hamsters, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int m = (leftIndex + rightIndex) / 2;

            mergeSort(hamsters, leftIndex, m);
            mergeSort(hamsters, m + 1, rightIndex);

            merge(hamsters, leftIndex, m, rightIndex);
        }
    }
}
