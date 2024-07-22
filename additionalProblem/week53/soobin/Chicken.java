import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Chicken {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 100000;

    private static int[] fibo;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        init();
        int min = getMin(N);
        int max = getMax(N);
        System.out.println(min + " " + max);
    }

    private static void init() {
        fibo = new int[MAX];
        fibo[1] = 1; fibo[2] = 2;
        for (int i = 3; i < MAX; i++)
            fibo[i] = fibo[i - 1] + fibo[i - 2];
    }

    private static int getMin(int N) {
        if (N % 2 == 0) return N / 2; // 전부 2인1닭
        return (N + 1) / 2; // (N - 3) / 2 + 2 // 3인2닭 1개 나머지 전부 2인1닭
    }

    private static int getMax(int N) {
        int[] memo = new int[MAX];
        memo[2] = 1;
        memo[3] = 2;
        for (int i = 4; i <= N; i++) {
            int max = 0;
            for (int k = 2; fibo[k] <= i; k++) {
                int p = fibo[k];
                max = Math.max(max, memo[p] + memo[i - p]);
            }
            memo[i] = max;
        }
        return memo[N];
    }
}
