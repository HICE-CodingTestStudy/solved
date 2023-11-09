package week24.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class GatheringMembers {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] problemsOfStudents;
    private static int answer, N, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            problemsOfStudents = new int[M];
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                int problems = 0;
                while (st.hasMoreTokens())
                    problems += 1 << (Integer.parseInt(st.nextToken()) - 1);
                problemsOfStudents[i] = problems;
            }
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(answer == Integer.MAX_VALUE ? -1 : answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int count(int mask) {
        int count = 0;
        String bitStr = Integer.toBinaryString(mask);
        for (char bit : bitStr.toCharArray())
            if (bit == '1') count++;
        return count;
    }

    private static void solution(int solved, int members, int i) {
        if (solved == (1 << N) - 1) {
            answer = Math.min(answer, count(members));
            return;
        }

        if (i == M - 1) return;

        solution(solved | problemsOfStudents[i + 1], members | (1 << (i + 1)), i + 1);
        solution(solved, members, i + 1);
    }

    public static void main(String[] args) {
        parseInput();
        solution(0, 0, -1);
        printOutput();
    }
}
