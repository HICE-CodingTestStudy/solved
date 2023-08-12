package stack;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CostLimit {
    //https://www.acmicpc.net/problem/1939
    //중량제한
    public static class Graph implements Comparable<Graph>{
        private int a;
        private int b;
        private int cost;

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getCost() {
            return cost;
        }


        public Graph(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Graph o) {
            if(this.cost>o.cost) return -1;
            if(this.cost<o.cost) return 1;
            else return 0;
        }
    }
    public static int find(int a, int[] graph){
        if(graph[a]==-1) return a;
        return graph[a]=find(graph[a],graph);
    }
    public static boolean union(int a, int b, int[] graph, int[] height){
        int rootA = find(a,graph);
        int rootB = find(b,graph);
        if(rootB==rootA) return false;
        if(rootA>=rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        if(height[rootA]==height[rootB]){
            graph[rootA]=rootB;
            height[rootA]=-1;
            height[rootB]++;
            return true;
        }
        graph[rootA]=rootB;
        height[rootA]=-1;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] graph = new int[N+1];
        int[] height = new int[N+1];
        Arrays.fill(graph,-1);
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int cost = sc.nextInt();
            pq.add(new Graph(A,B,cost));
            pq.add(new Graph(B,A,cost));
        }
        int A = sc.nextInt();
        int B = sc.nextInt();
        int ans = pq.peek().cost;
        while (!pq.isEmpty()){
            if(find(A,graph)==find(B,graph)){
                System.out.println(ans);
                return;
            }
            Graph g = pq.poll();
            if(union(g.getA(),g.getB(),graph,height)){
                ans = Math.min(ans,g.getCost());
            }

        }
    }
}
