package hamster;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static hamster.Main.searchingForHamster;
import static org.junit.jupiter.api.Assertions.assertEquals;

class mainTest {
    private static int allFood = 0;
    private static int hamstersAmount = 0;
    private static ArrayList<Hamster> hamsters = new ArrayList<>();

    private int hamsterTest(String filePath) {
        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
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

        return searchingForHamster(hamsters, hamstersAmount, allFood);
    }

    @Test
    void hamsterAmountTest() {
        assertEquals(hamsterTest("hamstr.in"), 2);
        assertEquals(hamsterTest("hamstr1.in"), 3);
        assertEquals(hamsterTest("hamstr2.in"), 1);
    }
}

