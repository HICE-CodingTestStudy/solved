package queue;

import java.util.*;

public class HeightOrder {
    //https://www.acmicpc.net/problem/2458
    //키순서
    public static int bfs(ArrayList<ArrayList<Integer>> graph, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        boolean[] visited = new boolean[graph.size() + 1];
        visited[startNode] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node != startNode) count++;
            int size = graph.get(node).size();
            for (int i = 0; i < size; i++) {
                //넣어줄때 방문 체크를 하지 않고, 꺼낼때 방문 체크를 하면 메모리 초과
                if (visited[graph.get(node).get(i)]) continue;
                visited[graph.get(node).get(i)] = true;
                queue.add(graph.get(node).get(i));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<ArrayList<Integer>> up = new ArrayList<>();
        ArrayList<ArrayList<Integer>> down = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            up.add(new ArrayList<>());
            down.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            up.get(a).add(b);
            down.get(b).add(a);
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int upCount = bfs(up, i);
            int downCount = bfs(down, i);
            if (upCount + downCount == N - 1) ans++;
        }
        System.out.println(ans);
    }
}
