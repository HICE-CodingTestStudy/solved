package week6.soobin;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class NearestCommonAncestor {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] tree;
    private static Set<Integer> ancestors;

    private static void traceAncestors(int v) {
        while (v != 0) {
            ancestors.add(v);
            v = tree[v];
        }
    }

    private static int findClosestAncestors(int v) {
        while (v != 0) {
            if (ancestors.contains(v))
                return v;
            v = tree[v];
        }

        return v;
    }

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            tree = new int[N + 1];
            ancestors = new HashSet<>();

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                tree[child] = parent;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            traceAncestors(u);
            bw.write(String.format("%d\n", findClosestAncestors(v)));
        }
        bw.flush();
    }
}