package Ing.week59.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23327_lolfm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        long[] arr = new long[N+1];
        long[] arr2 = new long[N+1];
        st = new StringTokenizer(br.readLine());
        int curr;
        for (int i = 1; i <= N; i++) {
            curr = Integer.parseInt(st.nextToken());
            arr[i] = curr + arr[i-1];
            arr2[i] = curr*curr + arr2[i-1];
        }


        StringBuilder sb = new StringBuilder();
        int l, r; long tmp;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            tmp = arr[r] - arr[l - 1];
            sb.append((tmp*tmp - (arr2[r]-arr2[l-1]))/2).append("\n");
        }
        System.out.print(sb);
    }
}