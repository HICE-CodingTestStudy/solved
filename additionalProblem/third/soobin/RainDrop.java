package third.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class RainDrop {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] blocks;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        blocks = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) blocks[i] = Integer.parseInt(st.nextToken());

        int height = 0, totalRain = 0;
        while (height < H) {
            boolean isBlocked = false;
            int rain = 0;
            for (int i = 0; i < W; i++) {
                if (blocks[i] > 0) {
                    isBlocked = true;

                    if (rain > 0) {
                        totalRain += rain;
                        rain = 0;
                    }
                }

                if (isBlocked && blocks[i] == 0)
                    rain++;

                if (blocks[i] > 0) blocks[i]--;
            }
            height++;
        }

        bw.write(String.valueOf(totalRain));
        bw.flush();
    }
}
