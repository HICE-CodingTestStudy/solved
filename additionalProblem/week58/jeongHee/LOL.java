package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LOL {
    // https://www.acmicpc.net/problem/23059
    // 리그 오브 레게노
    static Map<String, Integer> pre = new HashMap<>();
    static Map<String, List<String>> next = new HashMap<>();
    static Set<String> start = new HashSet<>();
    static void solution() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<String> pq = new PriorityQueue<>();
        int ans = 0;
        for (String s : start) {
            if(!pre.containsKey(s)) pq.add(s);
        }
        while (!pq.isEmpty()) {
            int size = pq.size();
            PriorityQueue<String> nPq = new PriorityQueue<>();
            while (size-- > 0) {
                String now = pq.poll();
                ans++;
                sb.append(now)
                  .append("\n");
                for (String s : next.getOrDefault(now, new ArrayList<>())) {
                    pre.put(s, pre.get(s) - 1);
                    if (pre.get(s) == 0) {
                        nPq.add(s);
                    }
                }
            }
            pq = nPq;
        }
        if(start.size()==ans) System.out.println(sb);
        else System.out.println(-1);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String fir = st.nextToken();
            String sec = st.nextToken();
            next.putIfAbsent(fir, new ArrayList<>());
            next.get(fir).add(sec);
            pre.put(sec, pre.getOrDefault(sec, 0) + 1);
            start.add(fir);
            start.add(sec);
        }
        solution();
    }
}
