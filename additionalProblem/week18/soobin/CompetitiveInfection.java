package week18.soobin;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CompetitiveInfection {
    private static class Virus {
        int row, col, type, time;

        Virus(int row, int col, int type, int time) {
            this.row = row;
            this.col = col;
            this.type = type;
            this.time = time;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static Queue<Virus> viruses;
    private static int[][] petri;
    private static int N, S, X, Y;

    private static void parseInput() {
        viruses = new PriorityQueue<>((o1, o2) -> o1.time != o2.time ? o1.time - o2.time : o1.type - o2.type);

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            petri = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int type = Integer.parseInt(st.nextToken());
                    petri[i][j] = type;
                    if (type != 0) viruses.add(new Virus(i, j, type, 0));
                }
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken()) - 1;
            Y = Integer.parseInt(st.nextToken()) - 1;
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(petri[X][Y]));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void infect() {
        while (!viruses.isEmpty()) {
            Virus virus = viruses.poll();
            if (virus.time == S) break;

            for (int[] move : moves) {
                int nr = virus.row + move[0], nc = virus.col + move[1];
                if (!isValid(nr, nc) || petri[nr][nc] > 0) continue;

                petri[nr][nc] = virus.type;
                viruses.add(new Virus(nr, nc, virus.type, virus.time + 1));
            }
        }
    }

    public static void main(String[] args) {
        parseInput();
        infect();
        printOutput();
    }
}
