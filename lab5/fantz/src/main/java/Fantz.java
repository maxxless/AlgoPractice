import static java.lang.Math.log;
import static java.lang.Math.min;
import static java.lang.Math.pow;


public final class Fantz {
    private final static int INFINITY = Integer.MAX_VALUE;

    private final static String binaryString = "101101101";
    private final static int maxElementNumber = binaryString.length();
    private final static int baseValue = 5;

    private static int findMinimalAmountOfPieces(int baseValue, String binaryString) {
        int[] arrayOfCounters = new int[maxElementNumber + 1];
        arrayOfCounters[0] = 0;

        for (int charIndex = 1; charIndex <= maxElementNumber; charIndex++) {
            arrayOfCounters[charIndex] = INFINITY;

            for (int subStringIndex = 1; subStringIndex <= charIndex; subStringIndex++) {
                if (binaryString.charAt(subStringIndex - 1) == '0') {
                    continue;
                }

                int tempBinaryPiece = Integer.parseInt(binaryString.substring(subStringIndex - 1, charIndex), 2);
                if (checkIfNumberIsAPower(tempBinaryPiece, baseValue)) {
                    arrayOfCounters[charIndex] = min(arrayOfCounters[charIndex], arrayOfCounters[subStringIndex - 1] + 1);
                }
            }
        }
        return (arrayOfCounters[maxElementNumber] == INFINITY) ? -1 : arrayOfCounters[maxElementNumber];
    }

    private static boolean checkIfNumberIsAPower(int value, int baseValue) {
        if (value == 0) {
            return false;
        }

        double number = log(value) / log(baseValue);
        return pow(baseValue, number) == value;
    }

    public static void main(String[] args) {
        int result = (!binaryString.contains("0")) ? maxElementNumber : findMinimalAmountOfPieces(baseValue, binaryString);

        System.out.println(result);
    }
}
