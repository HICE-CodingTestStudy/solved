package queue;

import java.util.Arrays;
import java.util.Scanner;

public class CycleGame {
    //https://www.acmicpc.net/problem/20040
    //사이클 게임

    public static int find(int a, int[] graph) {
        if (graph[a] == -1) return a;
        return graph[a] = find(graph[a], graph);
    }

    public static boolean union(int a, int b, int[] graph, int[] height) {
        int rootA = find(a, graph);
        int rootB = find(b, graph);
        if (rootB == rootA) return false;
        if (rootA >= rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        if (height[rootA] == height[rootB]) {
            graph[rootA] = rootB;
            height[rootA] = -1;
            height[rootB]++;
            return true;
        }
        graph[rootA] = rootB;
        height[rootA] = -1;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] graph = new int[N];
        int[] height = new int[N];
        Arrays.fill(graph, -1);
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(!union(a,b,graph,height)){
                System.out.println(i+1);
                return;
            }
        }
        System.out.println(0);
    }
}
