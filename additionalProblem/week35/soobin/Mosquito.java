import java.io.*;
import java.util.*;

public class Mosquito {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<Integer, Integer> map;
    private static int max = -1, start, end;

    public static void main(String[] args) {
        parseInput();
        solution();
        printAnswer();
    }

    private static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            map = new HashMap<>();

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                int start = Integer.parseInt(input[0]);
                int end = Integer.parseInt(input[1]);
                addMosquito(start, end);
            }
        } catch (IOException ignored) {}
    }

    private static void addMosquito(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
    }

    private static Queue<Integer> getSortedTime() {
        Queue<Integer> sorted = new PriorityQueue<>();
        sorted.addAll(map.keySet());
        return sorted;
    }

    private static void solution() {
        Queue<Integer> sortedTime = getSortedTime();
        boolean isInitiable = false;
        int current = 0;

        while (!sortedTime.isEmpty()) {
            int time = sortedTime.poll();
            current += map.get(time);

            if (current > max) {
                max = current;
                start = time;
                isInitiable = true;
                continue;
            }

            if (current < max && isInitiable) {
                end = time;
                isInitiable = false;
            }
        }
    }

    private static void printAnswer() {
        try {
            bw.write(max + "\n");
            bw.write(start + " " + end);
            bw.flush();
        } catch (IOException ignored) {}
    }
}
