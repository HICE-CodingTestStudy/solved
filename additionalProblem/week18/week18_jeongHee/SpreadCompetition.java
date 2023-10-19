package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SpreadCompetition {
    //https://www.acmicpc.net/problem/18405
    //경쟁적 전염
    static int N, K, S, X, Y;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Virus> pq = new PriorityQueue<>();

    static class Virus implements Comparable<Virus> {
        int i;
        int j;
        int virusNum;

        public Virus(int i, int j, int virusNum) {
            this.i = i;
            this.j = j;
            this.virusNum = virusNum;
        }

        @Override
        public int compareTo(Virus o) {
            return virusNum - o.virusNum;
        }
    }

    static void init() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    pq.add(new Virus(i, j, map[i][j]));
                }
            }
        }
        st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
    }

    static void spread() {
        int size = pq.size();
        PriorityQueue<Virus> nextPq = new PriorityQueue<>();
        while (size-- > 0) {
            Virus v = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = v.i + dx[i];
                int nextJ = v.j + dy[i];
                if (nextI < 0 || nextJ < 0 || nextJ >= N || nextI >= N) continue;
                if (map[nextI][nextJ] != 0) continue;
                map[nextI][nextJ] = v.virusNum;
                nextPq.add(new Virus(nextI, nextJ, v.virusNum));
            }
        }
        pq.addAll(nextPq);
    }


    public static void main(String[] args) throws IOException {
        init();
        while (S-->0) spread();
        System.out.println(map[X][Y]);
    }

}
