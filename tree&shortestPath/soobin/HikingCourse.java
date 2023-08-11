package soobin;

import java.util.*;

public class HikingCourse {
    private List<int[]>[] graph;
    private Set<Integer> summits;
    private Set<Integer> gates;
    private int[] intensities;

    private void addEdge(int[] edge) {
        int u = edge[0], v = edge[1], weight = edge[2];
        graph[u].add(new int[] {v, weight});
        graph[v].add(new int[] {u, weight});
    }

    private void dijkstra(int source) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[1]));
        intensities[source] = 0;
        pq.add(new int[] {source, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            if (summits.contains(u[0])) continue;

            if (intensities[u[0]] < u[1]) continue;

            for (int[] v : graph[u[0]]) {
                if (this.gates.contains(v[0]) || intensities[v[0]] <= Math.max(v[1], intensities[u[0]])) continue;

                intensities[v[0]] = Math.max(v[1], intensities[u[0]]);
                pq.add(v);
            }
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n + 1];
        this.summits = new HashSet<>();
        this.gates = new HashSet<>();
        intensities = new int[n + 1];

        for (int gate : gates) this.gates.add(gate);
        for (int summit : summits) this.summits.add(summit);
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            intensities[i] = Integer.MAX_VALUE;
        }

        for (int[] path : paths) addEdge(path);

        for (int gate : gates) dijkstra(gate);

        Arrays.sort(summits);
        int minSummit = summits[0], minIntensity = intensities[minSummit];
        for (int summit : summits)
            if (intensities[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = intensities[summit];
            }

        return new int[] {minSummit, minIntensity};
    }

    public static void main(String[] args) {
        HikingCourse hc = new HikingCourse();
        int[][] paths1 = new int[][] {{1,2,3}, {2,3,5}, {2,4,2}, {2,5,4}, {3,4,4}, {4,5,3}, {4,6,1}, {5,6,1}};
        int[][] paths2 = new int[][] {{1,4,4}, {1,6,1}, {1,7,3}, {2,5,2,}, {3,7,4}, {5,6,6}};
        int[][] paths3 = new int[][] {{1,2,5}, {1,4,1}, {2,3,1}, {2,6,7}, {4,5,1}, {5,6,1}, {6,7,1}};
        int[][] paths4 = new int[][] {{1,3,10}, {1,4,20}, {2,3,4}, {2,4,6}, {3,5,20}, {4,5,6}};

        int[] answer1 = hc.solution(6, paths1, new int[]{1,3}, new int[]{5});
        int[] answer2 = hc.solution(7, paths2, new int[]{1}, new int[]{2,3,4});
        int[] answer3 = hc.solution(7, paths3, new int[]{3,7}, new int[]{1,5});
        int[] answer4 = hc.solution(6, paths4, new int[]{1,2}, new int[]{5});

        System.out.println(answer1[0] + " " + answer1[1]);
        System.out.println(answer2[0] + " " + answer2[1]);
        System.out.println(answer3[0] + " " + answer3[1]);
        System.out.println(answer4[0] + " " + answer4[1]);
    }
}
