package week21.soobin;

import java.io.*;
import java.util.*;

public class CollectingDots {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer> rows, cols;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            rows = new ArrayList<>();
            cols = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                rows.add(r);
                cols.add(c);
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int sumMinDist(List<Integer> nums) {
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        int sum = 0;

        for (int num : nums) sum += Math.abs(num - median);

        return sum;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = sumMinDist(rows) + sumMinDist(cols);
        printOutput(answer);
    }
}
