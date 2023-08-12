package stack;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MakingConstellation {
    //https://www.acmicpc.net/problem/4386
    //별자리 만들기
    public static class Coordinate{
        private int index;
        private double x;
        private double y;

        public Coordinate(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        public int getIndex() {
            return index;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
    public static class Graph implements Comparable<Graph>{
        private Coordinate a;
        private Coordinate b;
        private double cost;

        public Coordinate getA() {
            return a;
        }

        public Coordinate getB() {
            return b;
        }

        public double getCost() {
            return cost;
        }

        public Graph(Coordinate a, Coordinate b) {
            this.a = a;
            this.b = b;
            this.cost = Math.sqrt(Math.pow((a.getX()-b.getX()),2)+Math.pow((a.getY()-b.getY()),2));
        }

        @Override
        public int compareTo(Graph o) {
            if(this.cost>o.cost) return 1;
            if(this.cost<o.cost) return -1;
            return 0;
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
        int n = sc.nextInt();
        double ans = 0.0;
        Coordinate[] arrayCoordinate = new Coordinate[n];
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        int[] graph = new int[n];
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            arrayCoordinate[i]=new Coordinate(i,sc.nextDouble(), sc.nextDouble());
            graph[i]=-1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                pq.add(new Graph(arrayCoordinate[i],arrayCoordinate[j]));
            }
        }
        while (!pq.isEmpty()){
            Graph g = pq.poll();
            if(union(g.getA().getIndex(),g.getB().getIndex(),graph,height))
                ans+=g.getCost();
        }
        System.out.println(String.format("%.2f",ans));
    }
}
