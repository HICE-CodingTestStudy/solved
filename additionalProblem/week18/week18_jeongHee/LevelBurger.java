package additional;

import java.math.BigInteger;
import java.util.Scanner;

public class LevelBurger {
    //https://www.acmicpc.net/problem/16974
    //레벨 햄버거

    //패티 수 저장
    static BigInteger[] dpP;
    //버거 길이 저장
    static BigInteger[] dp;
    static BigInteger X;
    static int N;
    static BigInteger ans = BigInteger.ZERO;

    static boolean solution() {
        //맨 처음
        if (X.equals(BigInteger.ONE) || X.equals(BigInteger.ZERO)) {
            if (N == 0 && X.equals(BigInteger.ONE)) ans = ans.add(BigInteger.ONE);
            return false;
        }
        //맨 끝
        if (X.equals(dp[N])) {
            ans = ans.add(dpP[N]);
            return false;
        }
        //정가운데
        if (X.equals(dp[N-1].add(BigInteger.valueOf(2)))){
            ans = ans.add(dpP[N-1]).add(BigInteger.ONE);
            return false;
        }
        //오른쪽
        if(X.compareTo(dp[N-1].add(BigInteger.valueOf(2)))>0){
            X = X.subtract(dp[N-1].add(BigInteger.valueOf(2)));
            ans = ans.add(dpP[N-1]).add(BigInteger.ONE);
            N--;
            return true;
        }
        //왼쪽
        X = X.subtract(BigInteger.ONE);
        N--;
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        X = sc.nextBigInteger();
        dp = new BigInteger[N + 1];
        dpP = new BigInteger[N + 1];
        dp[0] = BigInteger.ONE;
        dpP[0] = BigInteger.ONE;
        for (int i = 1; i < N + 1; i++) {
            dp[i] = dp[i - 1].multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(3));
            dpP[i] = dpP[i - 1].multiply(BigInteger.valueOf(2)).add(BigInteger.ONE);
        }
        while (true) {
            if (!solution()) break;
        }
        System.out.println(ans);
    }
}
