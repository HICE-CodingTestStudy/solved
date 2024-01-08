package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class UnderPrime {
    //https://www.acmicpc.net/problem/1124
    //언더프라임
    static int a, b;
    static Set<Integer> prime = new HashSet<>();
    static int[] dp;

    static void solution(int i) {
        if (dp[i] == 0) {
            prime.add(i);
            for (int j = i; j <= b; j++) {
                int nowJ = j;
                while (nowJ > 0) {
                    if (nowJ % i == 0) {
                        dp[j]++;
                        nowJ /= i;
                        continue;
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        dp = new int[b + 1];
        int ans = 0;
        for (int i = 2; i <= b; i++) {
            solution(i);
            if (i >= a && prime.contains(dp[i])) ans++;
        }
        System.out.println(ans);
    }
}
