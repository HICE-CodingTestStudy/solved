import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Assignment {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Queue<int[]> jobs = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
    private static int[] pointOfDay;
    private static int lastDeadline;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = countMaxPoint();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int day = Integer.parseInt(input[0]);
            int point = Integer.parseInt(input[1]);
            lastDeadline = Math.max(day, lastDeadline);
            jobs.add(new int[] {point, day});
        }
    }

    private static int countMaxPoint() {
        pointOfDay = new int[lastDeadline + 1];
        while (!jobs.isEmpty()) {
            int point = jobs.peek()[0], day = jobs.peek()[1];
            jobs.poll();

            while (day >= 1 && pointOfDay[day] != 0) day--;
            if (day > 0) pointOfDay[day] = point;
        }

        int maxPoint = 0;
        for (int i = 1; i <= lastDeadline; i++)
            maxPoint += pointOfDay[i];

        return maxPoint;
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
