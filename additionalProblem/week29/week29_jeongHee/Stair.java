package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Stair {
    //https://www.acmicpc.net/problem/21600
    //계단
    static int N;
    static long ans = 1;
    static List<Long> stairs = new ArrayList<>();
    static List<Long> dp = new ArrayList<>();

    static void solution() {
        dp.add(1L);
        for (int i = 1; i < stairs.size(); i++) {
            if (dp.get(i - 1) < stairs.get(i)) {
                dp.add(dp.get(i - 1) + 1);
                ans = Math.max(ans, dp.get(i));
            } else dp.add(stairs.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            stairs.add(Long.valueOf(st.nextToken()));
        }
        solution();
        System.out.println(ans);
    }
}
