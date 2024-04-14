import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW {
    //https://www.acmicpc.net/problem/13904
    //과제
    static int N;
    static List[] info = new ArrayList[1000];

    static int solution() {
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 999; i >= 0; i--) {
            pq.addAll(info[i]);
            if (pq.isEmpty()) continue;
            ans += pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < 1000; i++) {
            info[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            info[a - 1].add(b);
        }
        System.out.println(solution());
    }
}
