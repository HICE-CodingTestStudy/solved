package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Z {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] id = {{0, 1}, {2, 3}};

    private static int R;
    private static int C;
    private static int answer = 0;

    private static void splitBoard(int seed, int r, int c, int w) {
        if (r == R && c == C) {
            answer = seed;
            return;
        }

        w = w / 2;
        int sr = w == 1 ? R - r : (R - r) / w;
        int sc = w == 1 ? C - c : (C - c) / w;
        seed = (int) (seed + Math.pow(w, 2) * id[sr][sc]);
        splitBoard(seed, r + w * sr, c + w * sc, w);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N);
        splitBoard(0, 0, 0, N);

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
