import java.io.*;
import java.util.*;

public class Delivery {
    private static class Parcel {
        int from, to, num;

        Parcel(int from, int to, int num) {
            this.from = from;
            this.to = to;
            this.num = num;
        }

        public int getAvailable() {
            int max = 0;
            for (int i = from; i < to; i++)
                max = Math.max(max, truck[i]);

            return Math.min(num, limit - max);
        }

        public void addTruck(int parcels) {
            for (int i = from; i < to; i++)
                truck[i] += parcels;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<Parcel> villages;
    private static int[] truck;
    private static int limit;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws Exception {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        limit = Integer.parseInt(input[1]);
        villages = new PriorityQueue<>(Comparator.comparingInt(o -> o.to));
        truck = new int[N];

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;
            int num = Integer.parseInt(input[2]);
            villages.add(new Parcel(from, to, num));
        }
    }

    private static int solution() {
        int total = 0;
        while (!villages.isEmpty()) {
            Parcel p = villages.poll();

            int numAvailable = p.getAvailable();
            total += numAvailable;
            p.addTruck(numAvailable);
        }
        return total;
    }

    private static void printAnswer(int answer) throws Exception {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
