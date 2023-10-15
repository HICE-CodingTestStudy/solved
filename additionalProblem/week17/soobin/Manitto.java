package week17.soobin;

import java.io.*;
import java.util.*;

public class Manitto {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<String, String> manitto = new HashMap<>();
    private static Set<String> visited = new HashSet<>();
    private static int N, T = 1;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            if (N == 0) return;

            manitto.clear();
            visited.clear();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                manitto.put(st.nextToken(), st.nextToken());
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int numCycle) {
        try {
            bw.write(String.format("%d %d\n", T++, numCycle));
        } catch (IOException e) {}
    }

    private static void findCycle(String start, String current) {
        visited.add(current);
        if (start.equals(current)) return;

        findCycle(start, manitto.get(current));
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            parseInput();
            if (N == 0) break;

            int numCycle = 0;
            for (String person : manitto.keySet()) {
                if (visited.contains(person)) continue;
                findCycle(person, manitto.get(person));
                numCycle++;
            }
            printOutput(numCycle);
        }
        bw.flush();
    }
}
