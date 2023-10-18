package week17.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class MinMaxNum {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] numbers, minTree, maxTree;
    private static int N, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            numbers = new int[N + 1];
            for (int i = 1; i <= N; i++)
                numbers[i] = Integer.parseInt(br.readLine());
        } catch (IOException e) {}
    }

    private static void printOutput(int min, int max) {
        try {
            bw.write(String.format("%d %d\n", min, max));
        } catch (IOException e) {}
    }

    private static void buildSegTree() {
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, height + 1);
        minTree = new int[treeSize];
        maxTree = new int[treeSize];

        initialize(1, N, 1);
    }

    private static void initialize(int start, int end, int i) {
        if (start == end) {
            minTree[i] = maxTree[i] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;
        initialize(start, mid, 2 * i);
        initialize(mid + 1, end, 2 * i + 1);

        minTree[i] = Math.min(minTree[2 * i], minTree[2 * i + 1]);
        maxTree[i] = Math.max(maxTree[2 * i], maxTree[2 * i + 1]);
    }

    private static int findMin(int start, int end, int left, int right, int i) {
        if (right < start || end < left) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minTree[i];

        int mid = (start + end) / 2;
        int leftMin = findMin(start, mid, left, right, 2 * i);
        int rightMin = findMin(mid + 1, end, left, right, 2 * i + 1);
        return Math.min(leftMin, rightMin);
    }

    private static int findMax(int start, int end, int left, int right, int i) {
        if (right < start || end < left) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[i];

        int mid = (start + end) / 2;
        int leftMax = findMax(start, mid, left, right, 2 * i);
        int rightMax = findMax(mid + 1, end, left, right, 2 * i + 1);
        return Math.max(leftMax, rightMax);
    }

    private static void findMinMax(int start, int end) {
        int min = findMin(1, N, start, end, 1);
        int max = findMax(1, N, start, end, 1);
        printOutput(min, max);
    }

    private static void solution() {
        try {
            while (M-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                findMinMax(start, end);
            }
            bw.flush();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        parseInput();
        buildSegTree();
        solution();
    }
}
