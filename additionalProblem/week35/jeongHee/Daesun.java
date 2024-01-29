package stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Daesun {static int N;
    static List<List<Integer>> friendInfos = new ArrayList<>();
    static int[] scores;
    static int ansScore = Integer.MAX_VALUE;
    static List<Integer> ans = new ArrayList<>();

    static void solution(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            while (size-- > 0) {
                int now = queue.poll();
                scores[start] = Math.max(count, scores[start]);
                for (Integer friend : friendInfos.get(now)) {
                    if (visited[friend]) continue;
                    visited[friend] = true;
                    queue.add(friend);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        scores = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            friendInfos.add(new ArrayList<>());
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            friendInfos.get(a).add(b);
            friendInfos.get(b).add(a);
        }
        for (int i = 1; i < N + 1; i++) {
            solution(i);
            ansScore = Math.min(ansScore, scores[i]);
        }
        for (int i = 1; i < N + 1; i++) {
            if(scores[i]==ansScore) ans.add(i);
        }
        System.out.println(ansScore-1 + " " + ans.size());
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
