package tenth.soobin;

import java.io.*;
import java.util.*;

public class OrganicNapaCabbage {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static class NapaCabbage {
        int r, c;

        NapaCabbage(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Object obj) {
            NapaCabbage o = (NapaCabbage) obj;
            return r == o.r && c == o.c;
        }

        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    private static List<NapaCabbage> cabbages;
    private static boolean[][] field;

    public static void bfs(NapaCabbage start, int N, int M) {
        Queue<NapaCabbage> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        cabbages.remove(start);
        visited[start.r][start.c] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            NapaCabbage n = queue.poll();

            for (int[] move : moves) {
                int nr = n.r + move[0];
                int nc = n.c + move[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || !field[nr][nc])
                    continue;

                NapaCabbage next = new NapaCabbage(nr, nc);
                cabbages.remove(next);
                visited[nr][nc] = true;
                queue.add(next);
            }
        }
    }

    private static int solution(int N, int M, int K) throws IOException {
        field = new boolean[N][M];
        cabbages = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cabbages.add(new NapaCabbage(r, c));
            field[r][c] = true;
        }

        int worms = 0;
        while (!cabbages.isEmpty()) {
            NapaCabbage start = cabbages.get(0);
            bfs(start, N, M);
            worms++;
        }

        return worms;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            bw.write(String.format("%d\n", solution(N, M, K)));
        }

        bw.flush();
    }
}
