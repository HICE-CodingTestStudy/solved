import java.io.*;
import java.util.*;

public class JewelThief {
    private static class Jewel {
        int weight, price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Jewel> jewels = new ArrayList<>();
    private static List<Integer> bags = new ArrayList<>();
    private static int N;

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[1]);

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                int weight = Integer.parseInt(input[0]);
                int price = Integer.parseInt(input[1]);
                jewels.add(new Jewel(weight, price));
            }

            for (int i = 0; i < K; i++)
                bags.add(Integer.parseInt(br.readLine()));
        } catch (IOException ignored) {}
    }

    private static void printAnswer(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }

    private static void sort() {
        jewels.sort(
                (o1, o2) -> o1.weight == o2.weight
                        ? o2.price - o1.price
                        : o1.weight - o2.weight
        );
        Collections.sort(bags);
    }

    private static long solution() {
        long totalPrice = 0;

        int j = 0;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int bag : bags) {
            while (j < N && jewels.get(j).weight <= bag)
                pq.add(jewels.get(j++).price);

            if (!pq.isEmpty()) totalPrice += pq.poll();
        }

        return totalPrice;
    }

    public static void main(String[] args) {
        parseInput();
        sort();
        long answer = solution();
        printAnswer(answer);
    }
}
