import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static long N, M;
    static boolean flag;
    static Set<Long> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        flag = false;
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        set = new HashSet<>();
        divide(N);
        System.out.println(flag ? "YES" : "NO");
    }

    private static void divide(long num) {
        if (num < M || flag) {
            return;
        }
        if (num == M) {
            flag = true;
            return;
        }
        long half = num / 2;
        if (set.add(half)) {
            divide(half);
        }
        if (num % 2 != 0 && set.add(half + 1)) {
            divide(half + 1);
        }
    }
}