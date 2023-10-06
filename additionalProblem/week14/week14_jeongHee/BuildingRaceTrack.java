package com.example.demo.heap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BuildingRaceTrack {
    //https://school.programmers.co.kr/learn/courses/30/lessons/67259
    //경주로 건설
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dp;

    static class Coordinate {
        int i;
        int j;
        int lastDirection;
        int cost;

        public Coordinate(int i, int j, int lastDirection, int cost) {
            this.i = i;
            this.j = j;
            this.lastDirection = lastDirection;
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        int[][] dp = new int[board.length][board[0].length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE - 500);
        }
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0, 0, -1, 0));
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            if (c.i == board.length - 1 && c.j == board[0].length - 1) {
                ans = Math.min(ans, c.cost);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextI = c.i + dx[i];
                int nextJ = c.j + dy[i];
                if (nextI < 0 || nextJ < 0 || nextJ >= board[0].length || nextI >= board.length) continue;
                if (board[nextI][nextJ] == 1) continue;
                int additionalCost = 600;
                if (c.lastDirection == i || c.lastDirection == -1) additionalCost = 100;

                // 같은 칸에 도달할때까지의 비용자체가 적더라도 다음 칸을 꺾어서 가느냐 그냥 가느냐에 따라 최소비용이 달라질 수 있음
                // 500원 정도 차이는 극복 가능함
                if (dp[nextI][nextJ] + 500 >= c.cost + additionalCost) {
                    dp[nextI][nextJ] = Math.min(c.cost + additionalCost, dp[nextI][nextJ]);
                    queue.add(new Coordinate(nextI, nextJ, i, c.cost + additionalCost));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BuildingRaceTrack b = new BuildingRaceTrack();
        System.out.println(b.solution(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0}}));
    }
}
