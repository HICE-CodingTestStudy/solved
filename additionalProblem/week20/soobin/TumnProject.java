package week20.soobin;

import java.io.*;
import java.util.*;

public class TumnProject {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Set<Integer> studentsInTeam, studentsNotInTeam,  visited;
    private static int[] students;
    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            students = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++)
                students[i] = Integer.parseInt(st.nextToken());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.format("%d\n", answer));
        } catch (IOException e) {}
    }

    private static void findCycle(int start) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(start);
        visited.add(start);
        int last;

        while (true) {
            int next = students[deque.peekLast()];
            last = next;

            if (visited.contains(next)) break;

            if (studentsNotInTeam.contains(next)) {
                while (!deque.isEmpty()) studentsNotInTeam.add(deque.pollLast());
                return;
            }

            visited.add(next);
            deque.addLast(next);
        }

        while (!deque.isEmpty() && deque.peekLast() != last) {
            int inTeam = deque.pollLast();
            studentsInTeam.add(inTeam);
        }
        deque.pollLast();
        studentsInTeam.add(last);

        while (!deque.isEmpty()) {
            int notInTeam = deque.pollLast();
            studentsNotInTeam.add(notInTeam);
        }
    }

    private static int findStudentsNotInAnyTeam() {
        studentsInTeam = new HashSet<>();
        studentsNotInTeam = new HashSet<>();
        visited = new HashSet<>();

        for (int i = 1; i <= N; i++) {
            if (studentsInTeam.contains(i) || studentsNotInTeam.contains(i)) continue;

            visited.clear();
            findCycle(i);
        }

        return studentsNotInTeam.size();
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            parseInput();
            int answer = findStudentsNotInAnyTeam();
            printOutput(answer);
        }

        bw.flush();
    }
}
