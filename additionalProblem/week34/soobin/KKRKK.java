import java.io.*;

public class KKRKK {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[] input;
    private static int[] leftK, rightK;

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        print(answer);
    }

    private static void parseInput() {
        try {
            input = br.readLine().toCharArray();
        } catch (IOException ignored) {}
    }

    private static int solution() {
        int numR = countNumR();
        countLeftK(numR);
        countRightK(numR);
        return getMaxLength(numR);
    }

    private static int countNumR() {
        int numR = 0;
        for (char c : input) if (c == 'R') numR++;
        return numR;
    }

    private static void countLeftK(int numR) {
        leftK = new int[numR];
        int rIdx = 0, numK = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'K') numK++;
            else leftK[rIdx++] = numK;
        }
    }

    private static void countRightK(int numR) {
        rightK = new int[numR];
        int rIdx = numR - 1, numK = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] == 'K') numK++;
            else rightK[rIdx--] = numK;
        }
    }

    private static int getMaxLength(int numR) {
        int left = 0, right = numR - 1, max = 0;

        while (left <= right) {
            int length = (right - left + 1) + (2 * Math.min(leftK[left], rightK[right]));
            max = Math.max(max, length);

            if (leftK[left] < rightK[right]) left++;
            else right--;
        }

        return max;
    }

    private static void print(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
