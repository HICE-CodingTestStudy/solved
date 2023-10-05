package com.example.demo.heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SheepHitter {
    //https://www.acmicpc.net/problem/3187
    //양치기 꿍
    static boolean[][] visited;
    static Character[][] map;
    static ArrayList<Coordinate> wolfOrSheep = new ArrayList<>();
    static int R;
    static int C;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int wolf;
    static int sheep;

    public static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void bfs() {
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < wolfOrSheep.size(); i++) {
            Coordinate c = wolfOrSheep.get(i);
            if (visited[c.i][c.j]) continue;
            visited[c.i][c.j] = true;
            queue.add(c);
            int tmpWolf = 0;
            int tmpSheep = 0;
            while (!queue.isEmpty()) {
                Coordinate now = queue.poll();
                if (map[now.i][now.j] == 'v') tmpWolf++;
                else if(map[now.i][now.j] == 'k') tmpSheep++;
                for (int j = 0; j < 4; j++) {
                    int nextI = now.i + dx[j];
                    int nextJ = now.j + dy[j];
                    if (nextI < 0 || nextJ < 0 || nextI >= R || nextJ >= C) continue;
                    if (map[nextI][nextJ] == '#') continue;
                    if (visited[nextI][nextJ]) continue;
                    visited[nextI][nextJ] = true;
                    queue.add(new Coordinate(nextI, nextJ));
                }
            }
            if (tmpWolf >= tmpSheep) wolf += tmpWolf;
            else sheep += tmpSheep;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new Character[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'v' || map[i][j] == 'k')
                    wolfOrSheep.add(new Coordinate(i, j));
            }
        }
        bfs();
        System.out.println(sheep + " " + wolf);
    }
}