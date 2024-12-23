import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //https://www.acmicpc.net/problem/2631
    //줄세우기
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(bf.readLine());
            arr[i] = a;
        }
        int ans = 1;
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j]) max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N-ans);
    }
}