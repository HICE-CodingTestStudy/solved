import java.io.*;
import java.util.*;

public class ShoppingMall {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<int[]> counters, exitOrder;
    private static int K, counterId = 1;

    public static void main(String[] args) {
        initQueue();
        parseInput();
        long answer = exitCustomers();
        printAnswer(answer);
    }

    private static void initQueue() {
        // [0]: 카운터 번호 [1]: 대기 시간
        counters = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        // [0]: 회원 번호 [1]: 카운터 번호 [2]: 나가는 시간
        exitOrder = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) return o2[1] - o1[1];
            return o1[2] - o2[2];
        });
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                int id = Integer.parseInt(input[0]);
                int time = Integer.parseInt(input[1]);
                guideCustomerToCounter(id, time);
            }
        } catch (IOException ignored) {}
    }

    private static void guideCustomerToCounter(int id, int time) {
        if (counters.size() < K) {
            addQueue(id, counterId, time);
            counterId++;
        } else {
            int[] counter = counters.poll();
            time += counter[1];
            addQueue(id, counter[0], time);
        }
    }

    private static void addQueue(int customerId, int counterId, int time) {
        counters.add(new int[] {counterId, time});
        exitOrder.add(new int[] {customerId, counterId, time});
    }

    private static long exitCustomers() {
        long answer = 0;
        int count = 1;

        while (!exitOrder.isEmpty()) {
            int customerId = exitOrder.poll()[0];
            answer += (long) count * customerId;
            count++;
        }

        return answer;
    }

    private static void printAnswer(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
