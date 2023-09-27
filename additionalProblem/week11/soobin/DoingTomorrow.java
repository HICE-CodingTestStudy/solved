package week11.soobin;

import java.io.*;
import java.util.*;

public class DoingTomorrow {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> works = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int deadline = Integer.parseInt(st.nextToken());
            works.add(new int[] {cost, deadline});
        }

        int maxDay = works.peek()[1], maxWork = 1000000000;
        while(!works.isEmpty()) {
            int[] work = works.poll();

            if (work[1] > maxWork) continue;

            maxWork = Math.min(maxWork, work[1]);
            for (int i = 0; i < work[0]; i++) maxDay--;
        }

        bw.write(String.valueOf(maxDay));
        bw.flush();
    }
}
