package additional;

import java.util.Scanner;

public class Tetromino {
    //https://www.acmicpc.net/problem/14500
    //테트로미노
    static int N;
    static int M;
    static int answer = 0;
    static boolean[][] visited;

    public static void dfs(int[][] map, int i, int j, int count, int ans) {
        if (count == 4) {
            answer = Math.max(ans, answer);
            return;
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visited[i][j]=true;
        for (int k = 0; k < 4; k++) {
            if (i + dx[k] < 0 || j + dy[k] < 0 || i + dx[k] > N - 1 || j + dy[k] > M - 1)
                continue;
            if (visited[i + dx[k]][j + dy[k]]) continue;
            visited[i + dx[k]][j + dy[k]] = true;
            dfs(map, i + dx[k], j + dy[k], count + 1, ans + map[i + dx[k]][j + dy[k]]);
            visited[i + dx[k]][j + dy[k]] = false;
        }
        visited[i][j]=false;
    }
    public static void checkLast1(int[][] map, int i, int j){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int ans = map[i][j];
        for (int k = 0; k < 4; k++) {
            ans+=map[i+dx[k]][j+dy[k]];
        }
        for (int k = 0; k < 4; k++) {
            answer=Math.max(answer,ans-map[i+dx[k]][j+dy[k]]);
        }
    }

    public static void checkLast2(int[][] map){
        for (int k = 1; k < M-1; k++) {
            int ans = map[0][k]+map[0][k-1]+map[0][k+1]+map[1][k];
            answer=Math.max(answer,ans);
            ans = map[N-1][k]+map[N-1][k-1]+map[N-1][k+1]+map[N-2][k];
            answer=Math.max(answer,ans);
        }
        for (int k = 1; k < N-1; k++) {
            int ans = map[k][0]+map[k-1][0]+map[k+1][0]+map[k][1];
            answer=Math.max(answer,ans);
            ans = map[k][M-1]+map[k-1][M-1]+map[k+1][M-1]+map[k][M-2];
            answer=Math.max(answer,ans);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int[][] map = new int[N][M];
        visited =new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j]=sc.nextInt();
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(map,i,j,1,map[i][j]);
                if(i!=0&&j!=0&&i!=N-1&&j!=M-1)
                    checkLast1(map,i,j);
            }
        }
        checkLast2(map);
        System.out.println(answer);
    }
}
