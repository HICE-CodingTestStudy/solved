package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SeriouslyShuttleRun {
    // https://www.acmicpc.net/problem/23631
    // 진심 좌우 반복뛰기
    static int T, N, K;

    static void solution(StringBuilder sb) {
        int start = 0, d = 1, total = 0;
        int multi = 1;
        while (true) {
            total += (K * multi);
            start += (d * K * multi);
            if (total > N - 1) {
                if (d > 0) {
                    start -= (total - N + 1);
                } else start += (total - N + 1);
                sb.append(start).append(" ").append(d > 0 ? "R" : "L").append("\n");
                return;
            }
            multi++;
            d *= -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder ans = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            solution(ans);
        }
        System.out.println(ans);
    }
}
