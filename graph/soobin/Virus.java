package soobin;

import java.io.*;
import java.util.*;

public class Virus {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static List<Node>[] graph;
    private static Node[] nodes;

    public static class Node {
        public int id;
        public boolean visited;

        public Node(int id) {
            this.id = id;
            this.visited = false;
        }
    }

    private static void addEdge(int u, int v) {
        graph[u].add(nodes[v-1]);
        graph[v].add(nodes[u-1]);
    }

    private static int infect() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes[0]);
        nodes[0].visited = true;
        int answer = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Node neighbor : graph[node.id]) {
                if (!neighbor.visited) {
                    queue.add(neighbor);
                    neighbor.visited = true;
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
            graph[i+1] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            Scanner sc = new Scanner(br.readLine());
            addEdge(sc.nextInt(), sc.nextInt());
        }

        bw.write(String.valueOf(infect()));
        bw.flush();
    }
}
