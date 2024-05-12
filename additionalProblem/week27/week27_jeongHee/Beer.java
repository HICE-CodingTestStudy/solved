package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Beer {
    //https://www.acmicpc.net/problem/9205
    //맥주 마시면서 걸어가기
    static int N;
    static Coordinate house, target;
    static HashMap<Integer, Coordinate> hm;

    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calcDistance(Coordinate c) {
            return Math.abs(c.x - x) + Math.abs(c.y - y);
        }
    }

    static List<List<Integer>> graph;

    static void makeGraph() {
        for (Integer a : hm.keySet()) {
            for (Integer b : hm.keySet()) {
                if (a == b) continue;
                if (hm.get(a).calcDistance(hm.get(b)) <= 1000)
                    graph.get(a).add(b);
            }
        }
    }

    static void solution() {
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == N + 1) {
                System.out.println("happy");
                return;
            }
            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                if(visited.contains(next)) continue;
                visited.add(next);
                queue.add(next);
            }
        }
        System.out.println("sad");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            graph = new ArrayList<>();
            N = Integer.parseInt(bf.readLine());
            hm = new HashMap<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            house = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            hm.put(0, house);
            List<Coordinate> conv = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                conv.add(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                hm.put(i + 1, conv.get(i));
            }
            st = new StringTokenizer(bf.readLine());
            target = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            hm.put(N + 1, target);
            for (int i = 0; i < N + 2; i++) {
                graph.add(new ArrayList<>());
            }
            makeGraph();
            solution();
        }
    }
}
