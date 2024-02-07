package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LinkAndStart {
    //https://www.acmicpc.net/problem/15661
    //링크와 스타트
    static int N;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;

    private static void solution(boolean[] added, int count) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (added[i]) a.add(i);
            else b.add(i);
        }
        int aCount = 0, bCount = 0;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                aCount += map[a.get(i)][a.get(j)];
            }
        }
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                bCount += map[b.get(i)][b.get(j)];
            }
        }
        ans = Math.min(ans, Math.abs(aCount - bCount));
        if (count == N / 2) {
            return;
        }
        for (int i = 0; i < N; i++) {
            if (i < count) continue;
            if (added[i]) continue;
            added[i] = true;
            solution(added, i + 1);
            added[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a;
            }
        }
        solution(new boolean[N], 0);
        System.out.println(ans);
    }

}
