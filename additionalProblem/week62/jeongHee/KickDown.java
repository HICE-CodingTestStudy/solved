package jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class KickDown {
    // https://www.acmicpc.net/problem/1195
    // 킥다운
    static String large, small;

    static boolean isValid(BigInteger a, BigInteger b) {
        BigInteger sum = a.add(b);
        String sSum = String.valueOf(sum);
        return (sSum.indexOf('4') == -1);
    }

    static int solution(int count) {
        BigInteger iLarge = new BigInteger(large);
        BigInteger iSmall = new BigInteger(small);
        BigInteger move = new BigInteger(String.valueOf((int) Math.pow(10, count)));
        if (isValid(iLarge, iSmall.multiply(move))) return Math.max(large.length(), small.length() + count);

        if (count > large.length() - small.length()) {
            BigInteger rMove = new BigInteger(String.valueOf((int) Math.pow(10, count - (large.length() - small.length()))));
            if (isValid(iLarge, iSmall.divide(rMove))) return small.length() + count;
        }
        return solution(count + 1);
    }

    static boolean isValid(String fir, String sec) {
        for (int i = 0; i < Math.min(fir.length(), sec.length()); i++) {
            if (fir.charAt(i) == '2' && sec.charAt(i) == '2') return false;
        }
        return true;
    }

    static int solution2(int count) {
        if (isValid(large.substring(count), small)) return Math.max(large.length(), small.length() + count);
        if (count > large.length() - small.length())
            if (isValid(small.substring(count - (large.length() - small.length())), large))
                return small.length()+ count;
        return solution2(count + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        large = bf.readLine();
        small = bf.readLine();
        if (large.length() < small.length()) {
            String tmp = large;
            large = small;
            small = tmp;
        }
        System.out.println(solution2(0));
    }
}
