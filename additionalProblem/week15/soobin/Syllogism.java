package week15.soobin;

import java.io.*;
import java.util.*;

public class Syllogism {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int BASE = 97;

    private static Map<Integer, List<Integer>> graph;

    private static int convert(char c) {
        return (int) c - BASE;
    }

    private static void addRelation(char u, char v) {
        int idx = convert(u);
        int target = convert(v);
        graph.computeIfAbsent(idx, k -> new ArrayList<>()).add(target);
    }

    private static boolean isOneEqualAnother(int current, int target) {
        if (current == target) return true;

        if (!graph.containsKey(current)) return false;

        for (int n : graph.get(current))
            return isOneEqualAnother(n, target);

        return false;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String proposition = br.readLine();
            addRelation(proposition.charAt(0), proposition.charAt(5));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String proposition = br.readLine();
            int start = convert(proposition.charAt(0));
            int target = convert(proposition.charAt(5));
            bw.write(isOneEqualAnother(start, target) ? "T\n" : "F\n");
        }
        bw.flush();
    }
}
