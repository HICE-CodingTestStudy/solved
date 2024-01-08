package com.example.demo.heap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Othello {
    //https://www.acmicpc.net/problem/15671
    //오델로
    static char[][] board = new char[6][6];
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Stone {
        int i;
        int j;
        char color;
        int lastDirection;

        public Stone(int i, int j, char color, int lastDirection) {
            this.i = i;
            this.j = j;
            this.color = color;
            this.lastDirection = lastDirection;
        }
    }

    static void init() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = '.';
            }
        }
        board[2][2] = 'W';
        board[3][3] = 'W';
        board[2][3] = 'B';
        board[3][2] = 'B';
    }

    static void printResult() {
        int white = 0;
        int black = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(board[i][j]);
                if (board[i][j] == 'B') black++;
                else if (board[i][j] == 'W') white++;
            }
            System.out.println();
        }
        if (black > white) System.out.println("Black");
        else System.out.println("White");
    }

    //특정 줄의 돌을 뒤집음
    static void changeColor(int startI, int startJ, int endI, int endJ, int direction, char color) {
        while (!(startI == endI && startJ == endJ)) {
            board[startI][startJ] = color;
            startI += dx[direction];
            startJ += dy[direction];
        }
    }

    static void solution(Stone s) {
        boolean[][] visited = new boolean[6][6];
        visited[s.i][s.j] = true;
        Queue<Stone> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            Stone now = queue.poll();
            //뻗어져 나오다가 같은 색의 돌을 발견했을때 == 뒤집어야함
            if (now.color == s.color && !(now.i == s.i && now.j == s.j)) {
                changeColor(s.i, s.j, now.i, now.j, now.lastDirection, s.color);
                continue;
            }
            //이번턴에 돌을 놓은 자리라면 팔방으로 다 퍼져야함
            if (now.lastDirection == -1) {
                for (int i = 0; i < 8; i++) {
                    int nextI = now.i + dx[i];
                    int nextJ = now.j + dy[i];
                    if (nextI < 0 || nextI >= 6 || nextJ < 0 || nextJ >= 6) continue;
                    if (visited[nextI][nextJ]) continue;
                    if (board[nextI][nextJ] == '.' || board[nextI][nextJ] == s.color) continue;
                    queue.add(new Stone(nextI, nextJ, board[nextI][nextJ], i));
                    visited[nextI][nextJ] = true;
                }
                continue;
            }
            //뻗어져 나오는 중이라면 해당 방향으로만 가야함
            int nextI = now.i + dx[now.lastDirection];
            int nextJ = now.j + dy[now.lastDirection];
            if (nextI < 0 || nextI >= 6 || nextJ < 0 || nextJ >= 6) continue;
            if (visited[nextI][nextJ]) continue;
            if (board[nextI][nextJ] == '.') continue;
            queue.add(new Stone(nextI, nextJ, board[nextI][nextJ], now.lastDirection));
            visited[nextI][nextJ] = true;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        init();
        char color = 'B';
        for (int i = 0; i < N; i++) {
            int nextI = sc.nextInt() - 1;
            int nextJ = sc.nextInt() - 1;
            board[nextI][nextJ] = color;
            solution(new Stone(nextI, nextJ, color, -1));
            color = color == 'B' ? 'W' : 'B';
        }
        printResult();
    }

}
