package queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DownHill {
    //https://www.acmicpc.net/problem/1520
    //내리막길
    static long count = 0;

    static class Coordinate {
        int i;
        int j;
        String root;

        public Coordinate(int i, int j, String root) {
            this.i = i;
            this.j = j;
            this.root = root;
        }
    }

    public static void bfs(Coordinate startNode, Coordinate endNode, int[][] map) {
        HashMap<String, Boolean> visited = new HashMap<>();
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(startNode);
        visited.put(startNode.root, true);
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = c.i + dx[i];
                int nextJ = c.j + dy[i];
                if (nextI < 0 || nextI >= map.length || nextJ < 0 || nextJ >= map[0].length) continue;
                if (map[c.i][c.j] <= map[nextI][nextJ]) continue;
                if (!(nextI == endNode.i && nextJ == endNode.j) && map[nextI][nextJ] <= map[endNode.i][endNode.j])
                    continue;
                if (visited.get(c.root + "x" + nextI + "y" + nextJ) != null) {
                    continue;
                }
                if (nextI == endNode.i && nextJ == endNode.j) {
                    count++;
                    continue;
                }
                visited.put(c.root + "x" + nextI + "y" + nextJ, true);
                queue.add(new Coordinate(nextI, nextJ, c.root + "x" + nextI + "y" + nextJ));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] map = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        bfs(new Coordinate(0, 0, "x0y0"), new Coordinate(M - 1, N - 1, "E"), map);
        System.out.println(count);
    }
}
