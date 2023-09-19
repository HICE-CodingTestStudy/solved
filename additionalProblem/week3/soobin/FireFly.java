package week3.soobin;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FireFly {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] up; // 석순
    private static int[] down; // 종유석

    private static int bs(int target, int[] array) {
        int l = 0, r = array.length - 1;
        int mid;

        while (l <= r) {
            mid = (l + r) / 2;
            if (array[mid] < target) l = mid + 1;
            else r = mid - 1;
        }

        return l;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        down = new int[N / 2];
        up = new int[N / 2];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (i % 2 == 0) up[i / 2] = n;
            else down[(i - 1) / 2] = n;
        }

        Arrays.sort(down); Arrays.sort(up);
        PriorityQueue<Integer> sector = new PriorityQueue<>();

        /* 맨 아래 구간과 맨 위 구간 개수는 디폴트 값 */
        sector.add(N / 2); sector.add(N / 2);
        int L = N / 2 - 1;
        for (int i = 1; i < H - 1; i++) {
            /*
            * 해당 구간에 석순 개수 + 종유석 개수
            * 석순: 마지막 id - 높이가 (i+1)인 석순의 id + 1
            * 종유석: 마지막 id - 높이가 (H-i)인 종유석의 id + 1
            */
            int height = (L - bs(i + 1, up) + 1) + (L - bs(H - i, down) + 1);
            sector.add(height);
        }

        int min = sector.poll(), count = 1;
        while (!sector.isEmpty()) {
            int height = sector.poll();
            if (height > min) break;
            else count++;
        }

        bw.write(String.format("%d %d\n", min, count));
        bw.flush();
    }
}
