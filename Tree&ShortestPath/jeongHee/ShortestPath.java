package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPath {
    //https://www.acmicpc.net/problem/1753
    //최단 경로
    public static class Node{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    public static void dijkstra(int N, int source, List<Node>[] graph){
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
        for (int i = 1; i < N+1 ; i++) {
            if(dist[i]==Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int source = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        List<Node>[] graph = new List[V+1];
        for (int i = 0; i < V+1; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        dijkstra(V,source,graph);
    }

}
