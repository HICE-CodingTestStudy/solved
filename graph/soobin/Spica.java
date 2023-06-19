package soobin;

import java.io.*;
import java.util.*;

public class Spica {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static List<Integer>[] graph = new ArrayList[12];

    private static void addEdge(int u, int v) {
        graph[u].add(v);
        graph[v].add(u);
    }

    private static void getForm() {
        try {
            for (int i = 0; i < 12; i++) {
                Scanner sc = new Scanner(br.readLine());
                addEdge(sc.nextInt() - 1, sc.nextInt() - 1);
            }
        } catch (IOException e) {}
    }

    private static boolean isSpica(List<Integer> edges) {
        if (edges.size() != 3) return false;

        for (int u : edges) {
            if (graph[u].size() == 1) {
                for (int v : edges)
                    if (graph[v].size() == 2) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 12; i++) graph[i] = new ArrayList<>();
        getForm();

        for (int i = 0; i < 12; i++) {
            if (isSpica(graph[i])) {
                try {
                    bw.write(String.valueOf(i + 1));
                    bw.flush();
                } catch (IOException e) {}
                break;
            }
        }
    }
}
