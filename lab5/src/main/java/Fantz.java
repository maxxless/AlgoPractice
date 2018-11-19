import static java.lang.Math.log;
import static java.lang.Math.min;
import static java.lang.Math.pow;


public final class Fantz {
    private final static int BASE_VALUE = 5;
    private final static String BINARY_STRING = "101101101";

    private static int findMinimalAmountOfPieces(int baseValue, String binaryString) {
        char[] chars = binaryString.toCharArray();
        boolean hasZeros = false;

        for (char aChar : chars) {
            if (aChar == '0') {
                hasZeros = true;
                break;
            }
        }

        if (!hasZeros) {
            return chars.length;
        }

        int[] arrayOfCounters = new int[chars.length + 1];
        arrayOfCounters[0] = 0;

        for (int charIndex = 1; charIndex <= chars.length; charIndex++) {
            arrayOfCounters[charIndex] = Integer.MAX_VALUE;

            for (int subStringIndex = 1; subStringIndex <= charIndex; subStringIndex++) {
                if (chars[subStringIndex - 1] == '0') {
                    continue;
                }

//                char[] tempCharArray = new char[charIndex - subStringIndex + 1];
//                System.arraycopy(chars, subStringIndex - 1, tempCharArray, subStringIndex - 1, charIndex - (subStringIndex - 1));

                int tempNumberFromBinaryPiece = Integer.parseInt(String.valueOf(chars, subStringIndex - 1, charIndex - subStringIndex + 1), 2);

                if (checkIfNumberIsAPower(tempNumberFromBinaryPiece, baseValue)) {
                    arrayOfCounters[charIndex] = min(arrayOfCounters[charIndex], arrayOfCounters[subStringIndex - 1] + 1);
                }
            }
        }
        return (arrayOfCounters[chars.length] == Integer.MAX_VALUE) ? -1 : arrayOfCounters[chars.length];
    }

    private static boolean checkIfNumberIsAPower(int value, int baseValue) {
        if (value == 0) {
            return false;
        }

        double number = log(value) / log(baseValue);
        return pow(baseValue, number) == value;
    }

    public static void main(String[] args) {
        System.out.println(findMinimalAmountOfPieces(BASE_VALUE, BINARY_STRING));
    }
}
