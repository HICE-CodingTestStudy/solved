package additional;

import java.util.*;

public class Laboratory {
    //https://www.acmicpc.net/problem/14502
    //연구소
    static int answer = 0;
    static ArrayList<int[]> virus = new ArrayList<>();
    public static void bfs(int[][] map){
        int[][] tmpMap = new int[map.length][map[0].length];
        for (int i = 0; i < tmpMap.length; i++) {
            System.arraycopy(map[i], 0, tmpMap[i], 0, tmpMap[i].length);
        }
        boolean[][] isVisited = new boolean[tmpMap.length][tmpMap[0].length];
        Queue<int[]> queue = new LinkedList<>(virus);
        while (!queue.isEmpty()){
            int[] node = queue.poll();
            int i = node[0];
            int j = node[1];
            if(isVisited[i][j]) continue;
            isVisited[i][j]=true;
            tmpMap[i][j]=2;
            if(i-1>=0&&tmpMap[i-1][j]==0&&!isVisited[i-1][j]) queue.add(new int[]{i-1,j});
            if(i+1<tmpMap.length&&tmpMap[i+1][j]==0&&!isVisited[i+1][j]) queue.add(new int[]{i+1,j});
            if(j-1>=0&&tmpMap[i][j-1]==0&&!isVisited[i][j-1]) queue.add(new int[]{i,j-1});
            if(j+1<tmpMap[i].length&&tmpMap[i][j+1]==0&&!isVisited[i][j+1]) queue.add(new int[]{i,j+1});
        }
        int safeZone = 0;
        for (int i = 0; i < tmpMap.length; i++) {
            for (int j = 0; j < tmpMap[0].length; j++) {
                if(tmpMap[i][j]==0) safeZone++;
            }
        }
        answer=Math.max(answer,safeZone);
    }

    public static void solution(int[][] map, int addedWall, boolean[][] visited){
        if(addedWall==3){
            bfs(map);
            return;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]==0&& !visited[i][j]){
                    map[i][j]=1;
                    visited[i][j]=true;
                    solution(map,addedWall+1,visited);
                    visited[i][j]=false;
                    map[i][j]=0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j]=sc.nextInt();
                if(map[i][j]==2) virus.add(new int[]{i,j});
            }
        }
        solution(map,0,new boolean[map.length][map[0].length]);
        System.out.println(answer);
    }
}
