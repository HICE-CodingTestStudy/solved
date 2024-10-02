package ing.week57.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12886_stonegroup {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int max = a+b+c;
        boolean[][][] visited = new boolean[max+1][max+1][max+1];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] curr = {a, b, c};
        int[] next = new int[3];

        Arrays.sort(curr);
        q.offer(curr);
        visited[a][b][c] = true;
        boolean answer = false;

        int cnt = 0;
        loop:
        while(!q.isEmpty()) {
            curr = q.poll();
            for (int i = 0; i < 3; i++) {
                if(curr[i]==0) continue;
                for (int j = i+1; j < 3; j++) {
                    if(curr[i]==curr[j]) continue;
                    next[0] = curr[0];
                    next[1] = curr[1];
                    next[2] = curr[2];

                    next[i]+=curr[i];
                    next[j]-=curr[i];

                    Arrays.sort(next);
                    if(visited[next[0]][next[1]][next[2]]) continue;
                    if(next[0]==next[1] && next[1]==next[2]) {
                        answer = true;
                        break loop;
                    }
                    visited[next[0]][next[1]][next[2]] = true;
                    q.offer(next);
                    cnt++;
                    next = new int[3];
                }
            }
        }
        System.out.println(cnt);
        System.out.println(answer?1:0);
    }
}