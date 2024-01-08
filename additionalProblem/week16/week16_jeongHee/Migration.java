package com.example.demo.heap;

import java.util.Arrays;
import java.util.Scanner;

public class Migration {
    //https://www.acmicpc.net/problem/16234
    //인구이동
    static int N;
    static int L;
    static int R;
    static int[][][] graph;
    static int[][] height;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void init(){
        //union된 애들을 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(graph[i][j], -1);
            }
        }
        height = new int[N][N];
    }
    static int[] find(int i, int j) {
        if (graph[i][j][0] == -1 && graph[i][j][1] == -1) return new int[]{i, j};
        return graph[i][j] = find(graph[i][j][0], graph[i][j][1]);
    }

    static boolean union(int[] u, int[] v) {
        u = find(u[0], u[1]);
        v = find(v[0], v[1]);
        if (u[0] == v[0] && u[1] == v[1]) return false;
        if (height[u[0]][u[1]] >= height[v[0]][v[1]]) {
            int[] tmpU = new int[]{u[0], u[1]};
            u = new int[]{v[0], v[1]};
            v = new int[]{tmpU[0], tmpU[1]};
        }
        graph[u[0]][u[1]] = new int[]{v[0], v[1]};
        if (height[u[0]][u[1]] == height[v[0]][v[1]]) height[v[0]][v[1]]++;
        height[u[0]][u[1]] = 0;
        return true;
    }

    //국경을 열기
    static boolean open() {
        boolean ret = false;
        //모든 칸에 대해 사방을 검사해서 조건에 맞으면 union해준다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                    int diff = Math.abs(map[i][j] - map[nextI][nextJ]);
                    if (diff >= L && diff <= R) {
                        if (union(new int[]{i, j}, new int[]{nextI, nextJ})) {
                            ret = true;
                        }
                    }
                }
            }
        }
        //국경이 하나도 안열렸다면 false 하나라도 열렸다면 true
        return ret;
    }

    static void migration() {
        //특정 그룹의 루트 부분에 해당 그룹의 모든 인원수를 저장함
        int[][] people = new int[N][N];
        //특정 그룹의 그룹원수를 저장함
        int[][] count = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] root = find(i, j);
                people[root[0]][root[1]] += map[i][j];
                count[root[0]][root[1]]++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] root = find(i, j);
                map[i][j] = people[root[0]][root[1]] / count[root[0]][root[1]];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        graph = new int[N][N][2];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        init();
        int ans = 0;
        while (true) {
            if (!open()) {
                System.out.println(ans);
                return;
            }
            ans++;
            migration();
            init();
        }
    }
}

