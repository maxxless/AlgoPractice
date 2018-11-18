import static java.lang.Math.log;
import static java.lang.Math.min;
import static java.lang.Math.pow;


public final class Fantz {
    private final static int INFINITY = Integer.MAX_VALUE;

    private final static String BINARY_STRING = "101101101";
    private final static int MAX_ELEMENT_NUMBER = BINARY_STRING.length();
    private final static int BASE_VALUE = 5;

    private static int findMinimalAmountOfPieces(int baseValue, String binaryString) {
        int[] arrayOfCounters = new int[MAX_ELEMENT_NUMBER + 1];
        arrayOfCounters[0] = 0;

        for (int charIndex = 1; charIndex <= MAX_ELEMENT_NUMBER; charIndex++) {
            arrayOfCounters[charIndex] = INFINITY;

            for (int subStringIndex = 1; subStringIndex <= charIndex; subStringIndex++) {
                if (binaryString.charAt(subStringIndex - 1) == '0') {
                    continue;
                }

                int tempNumberFromBinaryPiece = Integer.parseInt(binaryString.substring(subStringIndex - 1, charIndex), 2);
                if (checkIfNumberIsAPower(tempNumberFromBinaryPiece, baseValue)) {
                    arrayOfCounters[charIndex] = min(arrayOfCounters[charIndex], arrayOfCounters[subStringIndex - 1] + 1);
                }
            }
        }
        return (arrayOfCounters[MAX_ELEMENT_NUMBER] == INFINITY) ? -1 : arrayOfCounters[MAX_ELEMENT_NUMBER];
    }

    private static boolean checkIfNumberIsAPower(int value, int baseValue) {
        if (value == 0) {
            return false;
        }

        double number = log(value) / log(baseValue);
        return pow(baseValue, number) == value;
    }

    public static void main(String[] args) {
        int result = (!BINARY_STRING.contains("0")) ? MAX_ELEMENT_NUMBER : findMinimalAmountOfPieces(BASE_VALUE, BINARY_STRING);

        System.out.println(result);
    }
}
