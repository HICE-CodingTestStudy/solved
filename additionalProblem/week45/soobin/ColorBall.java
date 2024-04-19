import java.io.*;
import java.util.*;

public class ColorBall {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int IDX = 0, COLOR = 1, SIZE = 2;
    private static final Map<Integer, List<Integer>> sizeByColor = new HashMap<>();
    private static final List<int[]> colorBalls = new ArrayList<>();

    private static List<Integer>[] sumByColor;
    private static int[] totalSum, answer;
    private static int N;

    public static void main(String[] args) throws IOException {
        parseInput();
        initializeBalls();
        initializeByColor();
        solution();
        printAnswer();
    }

    private static void parseInput() throws IOException {
        N = Integer.parseInt(br.readLine());
        sumByColor = new List[N + 1];
        totalSum = new int[N];
        answer = new int[N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int color = Integer.parseInt(input[0]);
            int size = Integer.parseInt(input[1]);
            if (sumByColor[color] == null) sumByColor[color] = new ArrayList<>();

            sizeByColor.computeIfAbsent(color, k -> new ArrayList<>()).add(size);
            colorBalls.add(new int[] {i, color, size});
        }
    }

    private static void initializeBalls() {
        colorBalls.sort(Comparator.comparingInt(o -> o[SIZE]));
        totalSum[0] = colorBalls.get(0)[SIZE];

        for (int i = 1; i < N; i++)
            totalSum[i] = totalSum[i - 1] + colorBalls.get(i)[SIZE];
    }

    private static void initializeByColor() {
        for (int color : sizeByColor.keySet()) {
            List<Integer> sizes = sizeByColor.get(color);
            Collections.sort(sizes);
            sizeByColor.put(color, sizes);

            sumByColor[color].add(sizes.get(0));
            for (int i = 1; i < sizes.size(); i++) {
                int prev = sumByColor[color].get(i - 1);
                sumByColor[color].add(prev + sizes.get(i));
            }
        }
    }

    private static void solution() {
        for (int[] ball : colorBalls) {
            int idx = findSmallerBall(ball[SIZE]);
            int idxByColor = findSmallerBallByColor(ball[COLOR], ball[SIZE]);

            if (idx >= 0) answer[ball[IDX]] += totalSum[idx];
            if (idxByColor >= 0) answer[ball[IDX]] -= sumByColor[ball[COLOR]].get(idxByColor);
        }
    }

    private static int findSmallerBall(int target) {
        int left = 0, right = N;

        while (left < right) {
            int mid = (left + right) / 2;
            if (colorBalls.get(mid)[SIZE] >= target) right = mid;
            else left = mid + 1;
        }
        return left - 1;
    }

    private static int findSmallerBallByColor(int color, int target) {
        List<Integer> sizes = sizeByColor.get(color);
        int left = 0, right = sizes.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (sizes.get(mid) >= target) right = mid;
            else left = mid + 1;
        }
        return left - 1;
    }

    private static void printAnswer() throws IOException {
        for (int i = 0; i < N; i++)
            bw.write(answer[i] + "\n");
        bw.flush();
    }
}
