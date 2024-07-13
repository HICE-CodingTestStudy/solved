import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Tiger {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] ddeok;
    private static boolean[][] visited;
    private static boolean isPossible;
    private static int[] answer;
    private static int N;

    public static void main(String[] args) throws Exception {
        parseInput();
        for (int n : ddeok[0]) {
            if (isPossible) break;
            visited[0][n] = true;
            answer[0] = n;
            solution(1, n);
        }
        if (!isPossible) System.out.println(-1);
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        ddeok = new List[N];
        answer = new int[N];
        visited = new boolean[N + 1][10];

        for (int i = 0; i < N; i++) {
            ddeok[i] = new ArrayList<>();
            String[] input = br.readLine().split(" ");
            int M = Integer.parseInt(input[0]);
            for (int j = 0; j < M; j++)
                ddeok[i].add(Integer.parseInt(input[j + 1]));
        }
    }

    private static void solution(int day, int prev) {
        if (day == N) {
            printAnswer();
            isPossible = true;
            return;
        }

        for (int n : ddeok[day]) {
            if (isPossible) return;
            if (n == prev || visited[day][n]) continue;
            visited[day][n] = true;
            answer[day] = n;
            solution(day + 1, n);
        }
    }

    private static void printAnswer() {
        try {
            for (int n : answer)
                bw.write(n + "\n");
            bw.flush();
        } catch (Exception ignored) {}
    }
}
