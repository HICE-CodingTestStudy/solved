package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BabyShark {
    //https://www.acmicpc.net/problem/16236
    //아기 상어
    static int[][] map;
    static int count = 0;

    static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static Coordinate bfs(Coordinate startNode, boolean[][] visited, int sharkSize, int N) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode.i][startNode.j] = true;
        Coordinate ret = new Coordinate(-1, -1);
        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, -1, 0, 1};
        int tmpCount = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinate c = queue.poll();
                if (map[c.i][c.j] != 0 && map[c.i][c.j] != 9 && map[c.i][c.j] < sharkSize) {
                    ret = c;
                    while (!queue.isEmpty() && ++i < size) {
                        Coordinate another = queue.poll();
                        if (!(map[another.i][another.j] != 0
                                && map[another.i][another.j] != 9
                                && map[another.i][another.j] < sharkSize)) continue;
                        if (ret.i > another.i) ret = another;
                        else if (ret.i == another.i && another.j < ret.j) ret = another;
                    }
                    count += tmpCount;
                    return ret;
                }
                for (int j = 0; j < 4; j++) {
                    if (c.i + di[j] >= 0 && c.i + di[j] < N && c.j + dj[j] >= 0 && c.j + dj[j] < N) {
                        if (!visited[c.i + di[j]][c.j + dj[j]]
                                && map[c.i + di[j]][c.j + dj[j]] <= sharkSize) {
                            queue.add(new Coordinate(c.i + di[j], c.j + dj[j]));
                            visited[c.i + di[j]][c.j + dj[j]] = true;
                        }
                    }
                }
            }
            tmpCount++;
        }
        return ret;
    }

    public static void solution(Coordinate shark, int N) {
        int ate = 0;
        int sharkSize = 2;
        Coordinate next = bfs(shark, new boolean[N][N], sharkSize, N);
        while (!(next.i == -1 && next.j == -1)) {
            map[next.i][next.j] = 9;
            map[shark.i][shark.j] = 0;
            shark = next;
            ate++;
            if (ate == sharkSize) {
                sharkSize++;
                ate = 0;
            }
            next = bfs(shark, new boolean[N][N], sharkSize, N);
        }
    }

    public static void main(String[] args) {
        //거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
        //이동 로직에 따라 이동했을때의 최소? / 그냥 물리적인 칸수로 따졌을때 최소?
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new int[N][N];
        Coordinate shark = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) shark = new Coordinate(i, j);
            }
        }
        solution(shark, N);
        System.out.println(count);
    }
}
