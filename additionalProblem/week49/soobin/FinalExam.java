import java.io.*;
import java.util.*;

public class FinalExam {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] input = br.readLine().split(" ");
        int time = Integer.parseInt(input[0]) * 24;
        int M = Integer.parseInt(input[1]);
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        int[] baseScore = new int[M];

        String[] input1 = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            int base = Integer.parseInt(input1[i]);
            int plus = Integer.parseInt(input2[i]);
            pq.add(new int[] {i, plus});
            baseScore[i] = base;
        }

        while (!pq.isEmpty()) {
            int idx = pq.peek()[0], plus = pq.peek()[1];
            int base = baseScore[idx];
            int studyTime = (100 - base) / plus;
            pq.poll();

            if ((100 - base) % plus > 0)
                pq.add(new int[] {idx, 100 - (base + studyTime * plus)});
            studyTime = Math.min(studyTime, time);
            time -= studyTime;
            baseScore[idx] += studyTime * plus;

            if (time == 0) break;
        }

        int total = 0;
        for (int score : baseScore)
            total += score;

        bw.write(String.valueOf(total));
        bw.flush();
    }
}
