package stack;

import java.util.Arrays;
import java.util.Scanner;

public class ExpressionOfSet {
    //https://www.acmicpc.net/problem/1717
    //집합의 표현
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
        int m = sc.nextInt();
        int[] graph = new int[n+1];
        int[] height = new int[n+1];
        Arrays.fill(graph, -1);
        for (int i = 0; i < m; i++) {
            if(sc.nextInt()==0){
                union(sc.nextInt(), sc.nextInt(),graph,height );
                continue;
            }
            if(find(sc.nextInt(),graph)==find(sc.nextInt(),graph))
                System.out.println("YES");
            else System.out.println("NO");
        }

    }
}
