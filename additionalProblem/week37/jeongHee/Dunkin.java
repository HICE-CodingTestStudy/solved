package queue;

import java.util.*;
import java.util.Queue;

public class Dunkin {
    //https://school.programmers.co.kr/learn/courses/30/lessons/258711
    //도넛과 막대
    static List<List<Integer>> graph = new ArrayList<>();
    static int createdNode;
    static int[] ans = new int[4];
    static int N;

    static class Node implements Comparable<Node> {
        int index;
        int out;

        @Override
        public int compareTo(Node o) {
            return o.out - out;
        }

        public Node(int index, int out) {
            this.index = index;
            this.out = out;
        }
    }

    static void count() {
        for (int i = 0; i < graph.get(createdNode).size(); i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[N];
            visited[graph.get(createdNode).get(i)] = true;
            queue.add(graph.get(createdNode).get(i));
            boolean isChecked = false;
            while (!queue.isEmpty()) {
                int now = queue.poll();
                if (graph.get(now).size() == 2) {
                    ans[3]++;
                    isChecked = true;
                    break;
                }
                if (graph.get(now).size() == 0) {
                    isChecked = true;
                    ans[2]++;
                    break;
                }
                if (visited[graph.get(now).get(0)]) {
                    isChecked = true;
                    ans[1]++;
                    break;
                }
                visited[graph.get(now).get(0)] = true;
                queue.add(graph.get(now).get(0));
            }
            if (!isChecked) ans[2]++;
        }
    }

    static public int[] solution(int[][] edges) {
        Set<Integer> isNotCreatedNode = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            while (graph.size() <= edges[i][0] || graph.size() <= edges[i][1]) graph.add(new ArrayList<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            isNotCreatedNode.add(edges[i][1]);
        }
        List<Node> tmp = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            tmp.add(new Node(i, graph.get(i).size()));
        }
        Collections.sort(tmp);
        N = tmp.size();
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).index == 0) continue;
            if (isNotCreatedNode.contains(tmp.get(i).index)) continue;
            createdNode = tmp.get(i).index;
            ans[0] = createdNode;
            break;
        }
        count();
        return ans;
    }

    public static void main(String[] args) {
        solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}});
    }
}
