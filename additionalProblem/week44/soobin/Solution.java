import java.io.*;

public class Solution {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] acidity;
    private static int N;

    public static void main(String[] args) throws IOException {
        parseInput();
        int[] answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        acidity = new int[N];
        for (int i = 0; i < N; i++)
            acidity[i] = Integer.parseInt(input[i]);
    }

    private static int[] solution() {
        if (acidity[0] > 0) return new int[] {acidity[0], acidity[1]};
        else if (acidity[N - 1] < 0) return new int[] {acidity[N - 2], acidity[N - 1]};

        int left = 0, right = N - 1;
        int first = acidity[left], second = acidity[right], min = first + second;

        while (left < right) {
            int leftAcidity = acidity[left], rightAcidity = acidity[right];
            if (leftAcidity + rightAcidity == 0)
                return new int[] {leftAcidity, rightAcidity};

            if (Math.abs(leftAcidity) > Math.abs(rightAcidity)) left++;
            else right--;

            if (Math.abs(min) > Math.abs(leftAcidity + rightAcidity)) {
                min = leftAcidity + rightAcidity;
                first = leftAcidity;
                second = rightAcidity;
            }
        }

        return new int[] {first, second};
    }

    private static void printAnswer(int[] answer) throws IOException {
        bw.write(answer[0] + " " + answer[1]);
        bw.flush();
    }
}
