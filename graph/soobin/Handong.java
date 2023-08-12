package soobin;

import java.io.*;
import java.util.*;

public class Handong {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] graph = new int[1001];
    private static List<Integer> met = new ArrayList<>();

    private static void trace(int n) {
        int current = n;

        while (true) {
            if (met.contains(current)) break;

            met.add(current);
            current = graph[current];
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) graph[i] = Integer.parseInt(br.readLine());

        int max = 0;
        int answer = 1;
        for (int i = 1; i <= N; i++) {
            trace(i);
            if (max < met.size()) {
                max = met.size();
                answer = i;
            }
            met.clear();
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
