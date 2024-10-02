package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ILikeIt {
    // https://www.acmicpc.net/problem/1253
    // 좋다
    static int N;
    static int count;
    static List<Integer> list = new ArrayList<>();

    static int solution() {
        int ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int s = list.get(i) + list.get(j);
                List<Integer> l = map.getOrDefault(s, new ArrayList<>());
                l.add(i);
                l.add(j);
                map.put(s, l);
            }
        }
        for (int i = 0; i < N; i++) {
            List<Integer> l = map.getOrDefault(list.get(i), new ArrayList<>());
            int cnt = l.size() / 2 - count;
            if (list.get(i) == 0) cnt++;
            if (cnt > 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            list.add(a);
            if (a == 0) count++;
        }
        System.out.println(solution());
    }
}
