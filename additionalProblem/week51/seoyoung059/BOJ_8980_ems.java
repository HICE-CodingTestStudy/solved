package fin.HICE.week51.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8980_ems {
    static class Boxes implements Comparable<Boxes> {
        int s;
        int e;
        int num;

        public Boxes(int s, int e, int num) {
            this.s = s;
            this.e = e;
            this.num = num;
        }

        @Override
        public int compareTo(Boxes o) {
            if (this.e == o.e) return o.s - this.s;
            return this.e - o.e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        Boxes[] arr = new Boxes[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Boxes(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr);

        int answer = 0, tmp;
        int[] truck = new int[n + 1];
        Boxes order;
        for (int i = 0; i < m; i++) {
            order = arr[i];
            tmp = order.num;
            for (int j = order.s; j < order.e; j++) {
                tmp = Math.min(c - truck[j], tmp);
            }
            answer += tmp;

            for (int j = order.s; j < order.e; j++) {
                truck[j] += tmp;
            }
        }
        System.out.println(answer);
    }
}
