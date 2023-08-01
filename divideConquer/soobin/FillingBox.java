package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class FillingBox {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] cubes;
    private static int answer = 0;
    private static long filled = 0;

    private static int min(int l, int w, int h) {
        return Math.min(l, Math.min(w, h));
    }

    private static void box(int l, int w, int h, int i) {
        if (l == 0 || w == 0 || h == 0 || i < 0) return;

        int k = min(l, w, h);
        i = Math.min((int) (Math.log(k) / Math.log(2)), i);

        int cube = 1 << i;
        if (cubes[i] > 0) {
            answer++;
            cubes[i]--;
            filled += (long) Math.pow(cube, 3);

            box(l - cube, cube, h, i);
            box(l, w - cube, h, i);
            box(cube, cube, h - cube, i);
        } else {
            box(l, w, h, i - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        cubes = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()); st.nextToken();
            cubes[i] = Integer.parseInt(st.nextToken());
        }

        box(length, width, height, n-1);
        answer = filled < (long) length * width * height ? -1 : answer;
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
