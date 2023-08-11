package soobin;

import java.util.*;

public class MapShortestPath {
    private Queue<int[]> queue;
    private boolean[][] visited;
    private int[][][] prev;

    private boolean isBlocked(int[][]maps, int[] node) {
        int row = node[0], col = node[1];
        return row < 0 || row >= maps.length || col < 0 || col >= maps[0].length || maps[row][col] == 0;
    }

    private void move(int[] from, int[] to) {
        queue.add(to);
        visited[to[0]][to[1]] = true;
        prev[to[0]][to[1]] = from;
    }

    private int path(int[] node) {
        List<int[]> path = new ArrayList<>();
        path.add(node);

        while (node[0] != 0 || node[1] != 0) {
            node = prev[node[0]][node[1]];
            path.add(node);
        }

        return path.size();
    }

    public int solution(int[][] maps) {
        queue = new LinkedList<>();
        visited = new boolean[maps.length][maps[0].length];
        prev = new int[maps.length][maps[0].length][2];

        queue.add(new int[]{0,0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int row = node[0], col = node[1];

            int[] up = new int[]{row - 1, col};
            int[] right = new int[]{row, col + 1};
            int[] down = new int[]{row + 1, col};
            int[] left = new int[]{row, col - 1};

            if (!isBlocked(maps, up) && !visited[up[0]][up[1]]) move(node, up);
            if (!isBlocked(maps, right) && !visited[right[0]][right[1]]) move(node, right);
            if (!isBlocked(maps, down) && !visited[down[0]][down[1]]) move(node, down);
            if (!isBlocked(maps, left) && !visited[left[0]][left[1]]) move(node, left);
        }

        return visited[maps.length - 1][maps[0].length - 1]
                ? path(new int[]{maps.length - 1, maps[0].length - 1})
                : -1;
    }

    public static void main(String[] args) {
        MapShortestPath msp = new MapShortestPath();
        int[][] maps = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}};
        int[][] maps2 = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}};
        System.out.println(msp.solution(maps));
        System.out.println(msp.solution(maps2));
    }
}
