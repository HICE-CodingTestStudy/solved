package week12.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MarbleEscape {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static class Marbles {
        int[] red, blue;
        int count;

        Marbles(int[] red, int[] blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    private static char[][] map;

    private static int[] copy(int[] source) {
        int[] copy = new int[2];
        copy[0] = source[0];
        copy[1] = source[1];
        return copy;
    }

    private static boolean isStuck(int[] before, int[] after) {
        return before[0] == after[0] && before[1] == after[1];
    }

    private static boolean redFirst(int[] red, int[] blue, int[] move) {
        if (move[0] > 0) return red[0] + move[0] > blue[0] + move[0];
        if (move[0] < 0) return red[0] + move[0] < blue[0] + move[0];
        if (move[1] > 0) return red[1] + move[1] > blue[1] + move[1];
        return red[1] + move[1] < blue[1] + move[1];
    }

    private static int[] move(int[] target, int[] other, int[] move) {
        int[] next = copy(target);
        int nR = next[0] + move[0];
        int nC = next[1] + move[1];

        while (map[nR][nC] != '#' && !((nR == other[0] && nC == other[1]) && map[nR][nC] != 'O')) {
            if (map[nR][nC] == '.' || map[nR][nC] == 'O') {
                next[0] = nR;
                next[1] = nC;
                if (map[nR][nC] == 'O') return next;
            }

            nR += move[0];
            nC += move[1];
        }

        return next;
    }

    private static int tilt(int[] redStart, int[] blueStart) {
        Queue<Marbles> queue = new LinkedList<>();
        queue.add(new Marbles(redStart, blueStart, 0));

        while(!queue.isEmpty()) {
            Marbles marbles = queue.poll();

            for (int[] move : moves) {
                int[] nRed, nBlue;
                if (redFirst(marbles.red, marbles.blue, move)) {
                    nRed = move(marbles.red, marbles.blue, move);
                    nBlue = move(marbles.blue, nRed, move);
                } else {
                    nBlue = move(marbles.blue, marbles.red, move);
                    nRed = move(marbles.red, nBlue, move);
                }

                if (isStuck(marbles.red, nRed) && isStuck(marbles.blue, nBlue)) continue;

                int count = marbles.count + 1;
                if (count > 10 || map[nBlue[0]][nBlue[1]] == 'O') continue;

                if (map[nRed[0]][nRed[1]] == 'O')
                    return map[nBlue[0]][nBlue[1]] != 'O' ? count : -1;

                queue.add(new Marbles(nRed, nBlue, count));
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int[] red = new int[2], blue = new int[2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == 'R') {
                    red[0] = i;
                    red[1] = j;
                    map[i][j] = '.';
                } else if (c == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                    map[i][j] = '.';
                } else map[i][j] = c;
            }
        }

        int answer = tilt(red, blue);
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
