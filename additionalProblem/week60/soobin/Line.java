import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Line {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] memo = new int[N];

        int lis = 1;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            memo[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] <= arr[j]) continue;
                memo[i] = Math.max(memo[i], memo[j] + 1);
            }

            lis = Math.max(lis, memo[i]);
        }

        System.out.println(N - lis);
    }
}
