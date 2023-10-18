package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WhereRUGoing {
    //https://www.acmicpc.net/problem/9370
    //미확인 도착지
    static class Node {
        int index;
        long cost;

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    static int n, m, t, s, g, h;
    static long gToH;
    static ArrayList<Integer> target = new ArrayList<>();
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static long[] dijkstra(int N, int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingLong(o1 -> o1.cost)
        );
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (dist[u.index] < u.cost) continue;
            for (Node v : graph.get(u.index)) {
                if (dist[v.index] <= dist[u.index] + v.cost) continue;
                dist[v.index] = dist[u.index] + v.cost;
                pq.add(new Node(v.index, dist[v.index]));
            }
        }
        return dist;
    }

    static ArrayList<Integer> solution() {
        long[] sToAll = dijkstra(n, s);
        long[] gToAll = dijkstra(n, g);
        long[] hToAll = dijkstra(n, h);
        HashSet<Integer> ret = new HashSet<>();
        for (Integer t : target) {
            if(hToAll[t]!=Long.MAX_VALUE){
                long dist = sToAll[g]+gToH+hToAll[t];
                if(dist<=sToAll[t]) ret.add(t);
            }
            if(gToAll[t]!=Long.MAX_VALUE){
                long dist = sToAll[h]+gToH+gToAll[t];
                if(dist<=sToAll[t]) ret.add(t);
            }
        }
        ArrayList<Integer> arr = new ArrayList<>(ret);
        Collections.sort(arr);
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            target = new ArrayList<>();
            graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
                if(a==g && b==h) gToH = d;
                if(a==h && b==g) gToH = d;
            }
            for (int i = 0; i < t; i++) {
                target.add(Integer.parseInt(bf.readLine()));
            }
            ArrayList<Integer> ret= solution();
            StringBuilder sb = new StringBuilder();
            for (Integer integer : ret) {
                sb.append(integer);
                sb.append(" ");
            }
            System.out.println(sb);
        }
    }
}
