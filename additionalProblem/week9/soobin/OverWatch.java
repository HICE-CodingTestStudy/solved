package week9.soobin;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class OverWatch {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static class Robot implements Comparable<Robot> {
        private int id;
        private double time;

        Robot(int id, int x, int y, int v) {
            this.id = id;
            this.time = getAttackedTime(x, y, v);
        }

        private double getAttackedTime(int x, int y, int v) {
            return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / v;
        }

        @Override
        public int compareTo(Robot o) {
            if (this.time == o.time) return this.id - o.id;
            return this.time > o.time ? 1 : -1;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Robot> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pq.add(new Robot(i + 1, x, y, v));
        }

        while (!pq.isEmpty())
            bw.write(String.format("%d\n", pq.poll().id));
        bw.flush();
    }
}
