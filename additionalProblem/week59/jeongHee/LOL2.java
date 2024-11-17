package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LOL2 {
    //https://www.acmicpc.net/problem/23327
    //리그전 오브 레전드
    static int N, Q;
    static long[] info;
    static long[] fSum, sum;
    static StringBuilder sb = new StringBuilder();

    static void init() {
        fSum = new long[N];
        sum = new long[N];
        fSum[0] =0;
        sum[0] = info[0];
        for (int i = 1; i < N; i++) {
            fSum[i] = fSum[i - 1] + sum[i - 1] * info[i];
            sum[i] = sum[i - 1] + info[i];
        }
    }

    static void solution(int s, int e) {
        long ans = fSum[e];
        if (s == 0) {
            sb.append(ans)
              .append("\n");
            return;
        }
        ans -= fSum[s - 1];
        ans -= (sum[e] - sum[s - 1]) * sum[s - 1];
        sb.append(ans)
          .append("\n");

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        info = new long[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            long a = Long.parseLong(st.nextToken());
            info[i] = a;
        }
        init();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            solution(s, e);
        }
        System.out.println(sb);
    }
}

