import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/5567
    //결혼식
    static int N;

    static int solution() {
        int ans = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            if(count==2) return ans;
            int size = queue.size();
            while (size-- > 0) {
                int now = queue.poll();
                for (Integer next : graph.get(now)) {
                    if(visited[next]) continue;
                    visited[next] = true;
                    queue.add(next);
                    ans++;
                }
            }
            count++;
        }
        return ans;
    }

    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        System.out.println(solution());
    }
}