package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class NumOfStreet {
    //https://www.acmicpc.net/problem/1577
    //도로의 개수
    static int N, M;
    static BigInteger[][] map;
    static Set<String> banned = new HashSet<>();

    static String makeKey(int a, int b, int c, int d) {
        return a + "," + b + "to" + c + "," + d;
    }

    static void init() {
        BigInteger now = BigInteger.ZERO;
        boolean flag = false;
        for (int i = 0; i < map[0].length; i++) {
            if (!flag) map[0][i] = now;
            else map[0][i] = BigInteger.ZERO;
            if (banned.contains(makeKey(0, i, 0, i + 1))) flag = true;
            now = BigInteger.ONE;
        }
        now = BigInteger.ZERO;
        flag = false;
        for (int i = 0; i < map.length; i++) {
            if (!flag) map[i][0] = now;
            else map[i][0] = BigInteger.ZERO;
            if (banned.contains(makeKey(i, 0, i + 1, 0))) flag = true;
            now = BigInteger.ONE;
        }
    }

    static void solution() {
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                map[i][j] = BigInteger.ZERO;
                if (!banned.contains(makeKey(i - 1, j, i, j)))
                    map[i][j] = map[i][j].add(map[i - 1][j]);
                if (!banned.contains(makeKey(i, j - 1, i, j)))
                    map[i][j] = map[i][j].add(map[i][j - 1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new BigInteger[N + 1][M + 1];
        int K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            banned.add(makeKey(a, b, c, d));
            banned.add(makeKey(c, d, a, b));

        }
        init();
        solution();
        System.out.println(map[N][M].toString());
    }
}
