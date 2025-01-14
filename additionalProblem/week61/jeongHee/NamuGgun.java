import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NamuGgun {
    //https://www.acmicpc.net/problem/1421
    //나무꾼 이다솜
    static long N, C, W;
    static List<Integer> namu = new ArrayList<>();
    static long minus, max;

    static long calc(long origin, long unit) {
        if (origin < unit) return 0;
        long m = origin / unit;
        if (origin % unit == 0)
            m--;
        if (m * C >= W * (origin / unit) * unit) return 0;
        minus += m;
        return origin / unit;
    }

    static long solution() {
        long ans = 0;
        for (int i = 1; i <= max; i++) {
            minus = 0;
            long sum = 0;
            for (Integer n : namu) {
                sum += calc((long) n, i);
            }
            long cost = sum *  W * (long) i - minus *  C;
            ans = Math.max(ans, cost);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        W = Long.parseLong(st.nextToken());

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(bf.readLine());
            max = Math.max(max, now);
            namu.add(now);
        }

        System.out.println(solution());
    }
}
