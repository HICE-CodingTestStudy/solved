import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Emoticon {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Queue<int[]> operations = new ArrayDeque<>();
    private static final boolean[][] visited = new boolean[10001][10001];

    private static int S;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = findMinOperation();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        S = Integer.parseInt(br.readLine());
    }

    private static int findMinOperation() {
        operations.add(new int[] {1, 0, 0});
        visited[1][0] = true;

        while (!operations.isEmpty()) {
            int monitor = operations.peek()[0], clip = operations.peek()[1], time = operations.peek()[2];
            operations.poll();

            if (monitor == S) return time;

            if (monitor != 0) {
                doOperation(monitor, monitor, time + 1);                // 클립보드 저장
                doOperation(monitor - 1, clip, time + 1);       // 삭제
            }
            if (clip != 0)
                doOperation(monitor + clip, clip, time + 1);    // 복사
        }

        return -1;
    }

    private static void doOperation(int monitor, int clip, int time) {
        if (visited[monitor][clip]) return;

        operations.add(new int[] {monitor, clip, time});
        visited[monitor][clip] = true;
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
