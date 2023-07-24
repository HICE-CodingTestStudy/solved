package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Zelda {
    public static class Node{
        int x;
        int y;
        int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
    public static void dijkstra(int N, int sourceX, int sourceY, List<Node>[][] graph, int[][] map){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1->o1.weight)
        );
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        dist[sourceX][sourceY] = map[sourceX][sourceY];
        pq.add(new Node(sourceX,sourceY,map[sourceX][sourceY]));
        while (!pq.isEmpty()){
            Node u = pq.poll();
            for (Node v : graph[u.x][u.y]) {
                if(dist[v.x][v.y]<=dist[u.x][u.y]+v.weight) continue;
                dist[v.x][v.y] = dist[u.x][u.y] + v.weight;
                pq.add(new Node(v.x, v.y, dist[v.x][v.y]));
            }
        }
        System.out.println(dist[N-1][N-1]);
    }
    public static void main(String[] args) throws IOException {
        //https://www.acmicpc.net/problem/4485
        //녹색 옷 입은 애가 젤다지?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if(N==0) return;
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j]=Integer.parseInt(st2.nextToken());
                }
            }
            List<Node>[][] graph = new List[N][N];
            for (int i = 0; i <N ; i++) {
                for (int j = 0; j <N ; j++) {
                    graph[i][j]=new ArrayList<>();
                    if(j-1>=0) graph[i][j].add(new Node(i,j-1,map[i][j-1]));
                    if(j+1<N) graph[i][j].add(new Node(i,j+1,map[i][j+1]));
                    if(i-1>=0) graph[i][j].add(new Node(i-1,j,map[i-1][j]));
                    if(i+1<N) graph[i][j].add(new Node(i+1,j,map[i+1][j]));
                }
            }
            System.out.print("Problem "+count+": ");
            dijkstra(N,0,0,graph,map);
            count++;
        }
    }
}
