package stack;

import java.util.Arrays;
import java.util.Scanner;

public class Lie {
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
        int know = sc.nextInt();
        for (int j = 0; j < know; j++) {
            union(0, sc.nextInt(),graph,height);
        }
        int[] party = new int[M];
        for (int i = 0; i < M; i++) {
            int numParty = sc.nextInt();
            int first = sc.nextInt();
            party[i]=first;
            for (int j = 1; j < numParty; j++) {
                int next = sc.nextInt();
                union(first,next,graph,height);
            }
        }
        int ans = M;
        for (int i = 0; i < M; i++) {
            if(find(party[i],graph)==find(0,graph))
                ans--;
        }
        System.out.println(ans);
    }
}
