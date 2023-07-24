package Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BaeMin {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12978
    //배달
    public static class Node{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    public static int dijkstra(int N, int source, List<Node>[] graph, int K){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1->o1.weight)
        );
        int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Node(source,0));
        while (!pq.isEmpty()){
            Node u = pq.poll();
            for (Node v : graph[u.index]) {
                if(dist[v.index]<=dist[u.index]+v.weight) continue;
                dist[v.index] = dist[u.index] + v.weight;
                pq.add(new Node(v.index, dist[v.index]));
            }
        }
        int ans = 0;
        for (int i = 1; i < N+1 ; i++) {
            if(dist[i]<=K) ans++;
        }
        return ans;
    }

    public int solution(int N, int[][] road, int K) {
        int E = road.length;
        int source = 1;
        List<Node>[] graph = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            graph[road[i][0]].add(new Node(road[i][1],road[i][2]));
            graph[road[i][1]].add(new Node(road[i][0],road[i][2]));
        }
        return dijkstra(N,source,graph,K);
    }
}
