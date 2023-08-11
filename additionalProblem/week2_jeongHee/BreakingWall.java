package additional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreakingWall {
    //https://www.acmicpc.net/problem/2206
    //벽 부수고 이동하기
    static int answer = Integer.MAX_VALUE;
    static class Coordinate{
        int i;
        int j;
        int distance;
        boolean breakWall;

        public Coordinate(int i, int j, int distance, boolean breakWall) {
            this.i = i;
            this.j = j;
            this.distance = distance;
            this.breakWall = breakWall;
        }
    }

    public static void bfs(int[][] map){
        //[i][j][0] -> 벽 부순 경우
        //[i][j][1] -> 안부순 경우
        boolean[][][] isVisited = new boolean[map.length][map[0].length][2];
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0,0,1,false));
        isVisited[0][0][1]=true;
        isVisited[0][0][0]=true;
        while (!queue.isEmpty()){
            Coordinate coordinate = queue.poll();
            int i = coordinate.i;
            int j = coordinate.j;
            if(i==map.length-1&&j==map[0].length-1){
                answer=Math.min(coordinate.distance,answer);
            }

            //다음 지점 방문
            if(i-1>=0) {
                addQueue(queue,map,coordinate,i-1,j,isVisited);
            }
            if(i+1<map.length) {
                addQueue(queue,map,coordinate,i+1,j,isVisited);
            }
            if(j-1>=0) {
                addQueue(queue,map,coordinate,i,j-1,isVisited);

            }
            if(j+1<map[i].length) {
                addQueue(queue,map,coordinate,i,j+1,isVisited);
            }
        }
    }

    public static void addQueue(Queue<Coordinate> queue, int[][] map, Coordinate coordinate,
                                int nextI, int nextJ, boolean[][][] visited){

        //벽이 아닌 경우
        if(map[nextI][nextJ]==0){
            //벽을 부수고 온 경우
            if(coordinate.breakWall && !visited[nextI][nextJ][0]){
                queue.add(new Coordinate(nextI,nextJ,coordinate.distance+1,true));
                visited[nextI][nextJ][0]=true;
            }

            //벽을 안부수고 온 경우
            if(!coordinate.breakWall && !visited[nextI][nextJ][1]){
                queue.add(new Coordinate(nextI,nextJ,coordinate.distance+1,false));
                visited[nextI][nextJ][1]=true;
            }
        }
        //벽인 경우
        else {
            //벽을 안부수고 온 경우
            if(!coordinate.breakWall && !visited[nextI][nextJ][0]){
                queue.add(new Coordinate(nextI,nextJ,coordinate.distance+1,true));
                visited[nextI][nextJ][0]=true;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j]=Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        bfs(map);
        if(answer==Integer.MAX_VALUE) answer =-1;
        System.out.println(answer);
    }
}
