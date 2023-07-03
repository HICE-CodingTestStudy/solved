package algoStudy.week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JeonJaeJoon {
    public static class Coordinate{
        private int x;
        private int y;
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(boolean[][] visited, Queue<Coordinate> queue, char[][] graph, int N, int i, int j){
        if(visited[i][j]) return -1;
        queue.add(new Coordinate(i, j));
        while (!queue.isEmpty()){
            Coordinate next = queue.poll();
            int x = next.getX();
            int y = next.getY();
            if(y-1>=0&&graph[x][y-1]==graph[x][y]&&!visited[x][y-1]){
                queue.add(new Coordinate(x,y-1));
                visited[x][y-1]=true;
            }
            if(y+1<N&&graph[x][y+1]==graph[x][y]&&!visited[x][y+1]){
                queue.add(new Coordinate(x,y+1));
                visited[x][y+1]=true;
            }
            if(x-1>=0&&graph[x-1][y]==graph[x][y]&&!visited[x-1][y]){
                queue.add(new Coordinate(x-1,y));
                visited[x-1][y]=true;
            }
            if(x+1<N&&graph[x+1][y]==graph[x][y]&&!visited[x+1][y]){
                queue.add(new Coordinate(x+1,y));
                visited[x+1][y]=true;
            }
        }
        return 1;
    }
    //https://www.acmicpc.net/problem/10026
    //적록 색약
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] normal = new char[N][N];
        char[][] jaeJoon = new char[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visitedJ = new boolean[N][N];
        Queue<Coordinate> queueJ = new LinkedList<>();
        int nor = 0;
        int jae = 0;
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                normal[i][j]=c;
                jaeJoon[i][j]=c;
                if(c=='G') jaeJoon[i][j]='R';
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(bfs(visited,queue,normal,N,i,j)==1)nor++;
                if(bfs(visitedJ,queueJ,jaeJoon,N,i,j)==1)jae++;
            }
        }
        System.out.print(nor);
        System.out.print(" ");
        System.out.print(jae);

    }
}
