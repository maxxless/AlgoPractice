import static java.lang.Math.log;
import static java.lang.Math.min;
import static java.lang.Math.pow;

public class Fantz {
    private final static long infinity = Integer.MAX_VALUE;

    private static int findMinimalAmountOfPieces(int baseValue, String binaryString) {
        long[] arrayOfCounters = new long[binaryString.length() + 1];
        arrayOfCounters[0] = 0;

        for (int charIndexInString = 1; charIndexInString <= binaryString.length(); charIndexInString++) {
            arrayOfCounters[charIndexInString] = infinity;

            for (int subStringIndex = 1; subStringIndex <= charIndexInString; subStringIndex++) {
                if (binaryString.charAt(subStringIndex - 1) == '0') {
                    continue;
                }

                int tempNumberFromSeparatedString = Integer.parseInt(binaryString.substring(subStringIndex - 1, charIndexInString), 2);
                if (checkIfNumberIsPowerOfBaseValue(tempNumberFromSeparatedString, baseValue)) {
                    arrayOfCounters[charIndexInString] = min(arrayOfCounters[charIndexInString], arrayOfCounters[subStringIndex - 1] + 1);
                }
            }
        }
        int result = (arrayOfCounters[binaryString.length()] == infinity) ? -1 : (int) arrayOfCounters[binaryString.length()];
        return result;
    }

    private static boolean checkIfNumberIsPowerOfBaseValue(long value, int baseValue) {
        if (value == 0) {
            return false;
        }

        int number = (int) (log(value) / log(baseValue));
        return pow(baseValue, number) == value;
    }

    public static void main(String[] args) {
        int baseValue = 5;
        String binaryString = "101101101";
        System.out.println(findMinimalAmountOfPieces(baseValue, binaryString));
    }
}
