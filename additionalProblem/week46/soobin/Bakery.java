import java.io.*;

public class Bakery {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, -1}, {0, -1}, {1, -1}};

    private static char[][] map;
    private static boolean[][] visited;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();
    }

    private static int solution() {
        int answer = 0;
        for (int i = 0; i < N; i++)
            if (dfs(i, M - 1)) answer++;
        return answer;
    }

    private static boolean dfs(int r, int c) {
        if (c == 0) return true;

        boolean isConnectable = false;
        visited[r][c] = true;

        for (int[] move : moves) {
            int nr = r + move[0], nc = c + move[1];
            if (isInvalid(nr, nc)) continue;
            isConnectable = isConnectable || dfs(nr, nc);
        }

        return isConnectable;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || visited[r][c] || map[r][c] == 'x';
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
