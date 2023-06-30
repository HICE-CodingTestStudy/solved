package soobin;

import java.util.*;

public class Network {
    private Set<Integer> visited;
    private List<Integer> unvisited;

    private void DFS(int n, int[][] computers) {
        visited.add(n);
        unvisited.remove(unvisited.indexOf(n));

        for (int i = 0; i < computers.length; i++)
            if (!visited.contains(i) && i != n && computers[n][i] == 1) DFS(i, computers);
    }
    public int solution(int n, int[][] computers) {
        visited = new HashSet<>();
        unvisited = new ArrayList<>();
        for (int i = 0; i < n; i++) unvisited.add(i);

        int answer = 0;
        while(!unvisited.isEmpty()) {
            int start = unvisited.get(0);
            DFS(start, computers);
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Network n = new Network();
        System.out.println(n.solution(3, new int[][]{{1,1,0}, {1,1,0}, {0,0,1}}));
        System.out.println(n.solution(3, new int[][]{{1,1,0}, {1,1,1}, {0,1,1}}));
    }
}
