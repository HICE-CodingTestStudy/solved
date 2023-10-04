package additional;

import java.util.ArrayList;
import java.util.Scanner;

public class CoinDistribution {
    //https://www.acmicpc.net/problem/1943
    //동전 분배

    //메모리 초과 풀이
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            int N = sc.nextInt();
            int sum = 0;
            ArrayList<Integer> coins = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int coin = sc.nextInt();
                int count = sc.nextInt();
                sum += coin * count;
                for (int k = 0; k < count; k++) {
                    coins.add(coin);
                }
            }
            if (sum % 2 == 1) {
                System.out.println(0);
                continue;
            }
            int[][] dp = new int[coins.size()][sum/2+1];
            for (int j = 1; j < dp.length; j++) {
                for (int k = 1; k < dp[j].length; k++) {
                    if (k - coins.get(j) >= 0)
                        dp[j][k] = Math.max(dp[j - 1][k], dp[j - 1][k - coins.get(j)] + coins.get(j));
                    else dp[j][k] = dp[j-1][k];
                }
            }
            if(dp[coins.size()-1][sum/2]==sum/2) System.out.println(1);
            else System.out.println(0);
        }
    }
}
