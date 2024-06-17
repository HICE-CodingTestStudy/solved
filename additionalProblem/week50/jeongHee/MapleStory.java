package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MapleStory {
    //https://www.acmicpc.net/problem/16457
    //단풍잎 이야기
    static int n, m, k, ans;
    static int[][] quest;

    static void solution(int count, int index, boolean[] visited) {
        if (count == n) {
            int tmpAns = 0;
            for (int i = 0; i < m; i++) {
                int clear = 0;
                for (int j = 0; j < k; j++) {
                    if (visited[quest[i][j]]) clear++;
                }
                if (clear == k) tmpAns++;
            }
            ans = Math.max(ans, tmpAns);
            return;
        }
        for (int i = index; i < 2 * n; i++) {
            visited[i] = true;
            solution(count + 1, i + 1, visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        quest = new int[m][k];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < k; j++) {
                quest[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
        solution(0, 0, new boolean[2 * n]);
        System.out.println(ans);
    }
}
