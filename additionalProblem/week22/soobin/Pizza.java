package week22.soobin;

import java.io.*;
import java.util.*;

public class Pizza {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] pizzas = new List[2], pizzaSum = new List[2];
    private static int K;

    private static void parseInput() {
        try {
            K = Integer.parseInt(br.readLine());
            pizzas[0] = new ArrayList();
            pizzas[1] = new ArrayList();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            for (int i = 0; i < M; i++)
                pizzas[0].add(Integer.parseInt(br.readLine()));
            for (int i = 0; i < N; i++)
                pizzas[1].add(Integer.parseInt(br.readLine()));
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void countPiecesSum(int store) {
        List<Integer> pizza = pizzas[store];
        pizzaSum[store] = new ArrayList<>();

        int numPizza = pizza.size(), sum = 0;
        for (int i = 0; i < numPizza; i++) {
            sum = 0;
            for (int j = 0; j < numPizza - 1; j++) {
                sum += pizza.get(j);
                pizzaSum[store].add(sum);
            }

            int firstPiece = pizza.get(0);
            pizza.add(firstPiece);
            pizza.remove(0);
        }

        pizzaSum[store].add(0);
        pizzaSum[store].add(sum + pizza.get(numPizza - 2)); // 모든 조각 포함
    }

    private static int binarySearch(int target, int pizza) {
        List<Integer> sums = pizzaSum[pizza];
        int left = 0, right = sums.size();

        if (sums.get(right - 1) < target) return -1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (sums.get(mid) < target) left = mid + 1;
            else right = mid;
        }

        int count = 0;
        for (int i = left; i < sums.size() && sums.get(i) == target; i++) count++;

        return count == 0 ? -1 : count;
    }

    private static int countTotalCases() {
        Collections.sort(pizzaSum[0]);
        Collections.sort(pizzaSum[1]);
        int total = 0;

        for (int i = 0; i <= K; i++) {
            int a = binarySearch(i, 0);
            int b = binarySearch(K - i, 1);
            if (a == -1 || b == -1) continue;

            total += a * b;
        }

        return total;
    }

    public static void main(String[] args) {
        parseInput();
        countPiecesSum(0);
        countPiecesSum(1);
        int answer = countTotalCases();
        printOutput(answer);
    }
}
