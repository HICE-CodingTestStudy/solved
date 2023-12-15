import java.io.*;
import java.util.StringTokenizer;

public class ComeBackHome {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static char[][] map;
    private static boolean[][] visited;
    private static int R, C, K, answer;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            visited = new boolean[R][C];

            for (int i = 0; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++)
                    map[i][j] = line.charAt(j);
            }
        } catch(IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void solution(int r, int c, int dist) {
        if (r == 0 && c == C - 1) {
            if (dist == K) answer++;
            return;
        }

        for (int[] move : moves) {
            int nr = r + move[0], nc = c + move[1];
            if (!isValid(nr, nc) || visited[nr][nc] || map[nr][nc] == 'T') continue;

            visited[nr][nc] = true;
            solution(nr, nc, dist + 1);
            visited[nr][nc] = false;
        }
    }

    public static void main(String[] args) {
        parseInput();
        visited[R - 1][0] = true;
        solution(R - 1, 0, 1);
        printAnswer(answer);
    }
}
