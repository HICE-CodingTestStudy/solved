package additional2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class CaptainDaSom {
    //https://www.acmicpc.net/problem/1660
    //캡틴 이다솜
    static ArrayList<Integer> triangle = new ArrayList<>();
    static ArrayList<Integer> tetrahedron = new ArrayList<>();
    static int[] dp;
    static int N;

    static void solution(){
        for (int i = 2; i <=N; i++) {
            for (int j = 1; j <= N; j++) {
                if(j>=tetrahedron.size()) break;
                if(tetrahedron.get(j)>i) break;
                dp[i] = Math.min(dp[i], dp[i - tetrahedron.get(j)] + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        triangle.add(0);
        triangle.add(1);
        tetrahedron.add(0);
        tetrahedron.add(1);
        dp = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            triangle.add(triangle.get(i - 1) + i);
            tetrahedron.add(tetrahedron.get(i - 1) + triangle.get(i));
            if (tetrahedron.get(i) >= N) {
                break;
            }
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (Integer i : tetrahedron) {
            if(i>=N+1) continue;
            dp[i] = 1;
        }
        solution();
        System.out.println(dp[N]);
    }
}
