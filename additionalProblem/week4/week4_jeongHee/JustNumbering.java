package queue;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class JustNumbering {
    //https://www.acmicpc.net/problem/2667
    //단지번호 붙이기
    static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static Coordinate find(int i, int j, Coordinate[][] graph) {
        if (graph[i][j].i == -1 && graph[i][j].j == -1) return new Coordinate(i, j);
        return graph[i][j] = find(graph[i][j].i, graph[i][j].j, graph);
    }

    public static boolean union(int ai, int aj, int bi, int bj, Coordinate[][] graph, int[][] height) {
        Coordinate rootA = find(ai, aj, graph);
        Coordinate rootB = find(bi, bj, graph);
        if (rootB.i == rootA.i && rootB.j == rootA.j) return false;
        if (height[rootA.i][rootA.j] >= height[rootB.i][rootB.j]) {
            Coordinate tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        if (height[rootA.i][rootA.j] == height[rootB.i][rootB.j]) {
            graph[rootA.i][rootA.j] = rootB;
            height[rootA.i][rootA.j] = -1;
            height[rootB.i][rootB.j]++;
            return true;
        }
        graph[rootA.i][rootA.j] = rootB;
        height[rootA.i][rootA.j] = -1;
        return true;
    }

    public static boolean move(int iMax, int jMax, int i, int j) {
        if (i < 0 || i >= iMax) return false;
        if (j < 0 || j >= jMax) return false;
        return true;
    }

    public static void solution(int N, int[][] apartment, Coordinate[][] graph, int[][] height) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (apartment[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    if (!move(N, N, i + dx[k], j + dy[k])) continue;
                    if (apartment[i + dx[k]][j + dy[k]] == 1) {
                        union(i, j, i + dx[k], j + dy[k], graph, height);
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (apartment[i][j] == 0) continue;
                Coordinate c = find(i, j, graph);
                //x y 를 안붙이면
                //12, 4 일때랑 1, 24 일때랑 똑같음...
                String key = "x" + String.valueOf(c.i) + "y" + String.valueOf(c.j);
                if (hashMap.get(key) == null) {
                    hashMap.put(key, 1);
                    continue;
                }
                hashMap.put(key, hashMap.get(key) + 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(hashMap.values());
        int size = pq.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            System.out.println(pq.poll());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Coordinate[][] graph = new Coordinate[N][N];
        int[][] apartment = new int[N][N];
        int[][] height = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < N; j++) {
                apartment[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
                graph[i][j] = new Coordinate(-1, -1);
                height[i][j] = 1;
            }
        }
        solution(N, apartment, graph, height);
    }
}
