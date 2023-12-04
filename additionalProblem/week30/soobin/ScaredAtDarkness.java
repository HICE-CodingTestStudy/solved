package week30.soobin;

import java.io.*;
import java.util.*;

public class ScaredAtDarkness {
    private static class Area {
        int r1, c1, r2, c2;

        Area(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }

        public int getCount() {
            return (r2 - r1 + 1) * (c2 - c1 + 1);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Area> areas = new ArrayList<>();
    private static int[][] pixels, brightness;
    private static int R, C, Q;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            brightness = new int[R][C];
            pixels = new int[R][C];

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    pixels[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;
                areas.add(new Area(r1, c1, r2, c2));
            }
        } catch (IOException e) {}
    }

    private static int getBrightness(int r, int c) {
        if (r == 0 && c == 0) return pixels[0][0];
        if (r < 0 || c < 0) return 0;

        if (brightness[r][c] > 0) return brightness[r][c];

        return brightness[r][c] = pixels[r][c]
                + getBrightness(r - 1, c)
                + getBrightness(r, c - 1)
                - getBrightness(r - 1, c - 1);
    }

    private static void solution() {
        try {
            for (Area area : areas) {
                int brightness = getBrightness(area.r2, area.c2);
                if (area.r1 >= 1) brightness -= getBrightness(area.r1 - 1, area.c2);
                if (area.c1 >= 1) brightness -= getBrightness(area.r2, area.c1 - 1);
                if (area.r1 >= 1 && area.c1 >= 1) brightness += getBrightness(area.r1 - 1, area.c1 - 1);

                bw.write(String.format("%d\n", brightness / area.getCount()));
            }
            bw.flush();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        parseInput();
        solution();
    }
}
