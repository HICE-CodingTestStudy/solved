package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Diet {
    //https://www.acmicpc.net/problem/19942
    //다이어트
    static boolean[] ans;
    static int ansCost = Integer.MAX_VALUE;
    static int[][] info;
    static int mp, mf, ms, mv;

    static boolean isDone(int[] ate) {
        return ate[0] >= mp && ate[1] >= mf && ate[2] >= ms && ate[3] >= mv;
    }

    static void solution(boolean[] selected, int[] ate, int cost, int index) {
        if (cost >= ansCost) return;
        if (isDone(ate)) {
            ansCost = cost;
            ans = selected.clone();
            return;
        }
        for (int i = index; i < info.length; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            for (int j = 0; j < 4; j++) {
                ate[j] += info[i][j];
            }
            solution(selected, ate, cost + info[i][4], i + 1);
            for (int j = 0; j < 4; j++) {
                ate[j] -= info[i][j];
            }
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        info = new int[N][5];
        st = new StringTokenizer(bf.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 5; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution(new boolean[N], new int[4], 0, 0);
        StringBuilder sb = new StringBuilder();
        if (ansCost == Integer.MAX_VALUE)
            System.out.println(-1);
        else {
            System.out.println(ansCost);
            for (int i = 0; i < ans.length; i++) {
                if (!ans[i]) continue;
                sb.append(i + 1).append(" ");
            }
            System.out.println(sb);
        }
    }
}
