package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RainDrop {
    //https://www.acmicpc.net/problem/17073
    //나무 위의 빗물
    static List<List<Integer>> graph = new ArrayList<>();
    static int N, W;

    static int solution() {
        int leaf = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (graph.get(node).size() == 1 && node != 1) {
                leaf++;
                continue;
            }
            for (int i = 0; i < graph.get(node).size(); i++) {
                if (visited[graph.get(node).get(i)]) continue;
                visited[graph.get(node).get(i)] = true;
                queue.add(graph.get(node).get(i));
            }
        }
        return leaf;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        System.out.printf("%.10f", (double) W / (double) solution());
    }
}
