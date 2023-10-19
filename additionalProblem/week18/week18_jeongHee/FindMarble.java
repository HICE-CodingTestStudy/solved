package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FindMarble {
    //https://www.acmicpc.net/problem/2617
    //구슬 찾기
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> heavier = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> lighter = new ArrayList<>();

    public static HashSet<Integer> bfs(ArrayList<ArrayList<Integer>> graph) {
        HashSet<Integer> ans = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            HashSet<Integer> count = new HashSet<>();
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int now = queue.poll();
                count.addAll(graph.get(now));
                if (count.size() > (N - 1) / 2) {
                    ans.add(i);
                    break;
                }
                for (Integer next : graph.get(now)) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + 1; i++) {
            heavier.add(new ArrayList<>());
            lighter.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lighter.get(a).add(b);
            heavier.get(b).add(a);
        }
        HashSet<Integer> hs = bfs(lighter);
        hs.addAll(bfs(heavier));
        System.out.println(hs.size());
    }
}
