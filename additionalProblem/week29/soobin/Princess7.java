package week29.soobin;

import java.io.*;
import java.util.*;

public class Princess7 {
    private static class Student {
        int row, col;
        char type;

        Student(int row, int col, char type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }

        public boolean equals(Object o) {
            return ((Student) o).row == row && ((Student) o).col == col;
        }

        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static List<List<Student>> combinations = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static boolean[] selected = new boolean[25];

    private static void parseInput() {
        try {
            for (int i = 0; i < 5; i++) {
                String line = br.readLine();
                for (int j = 0; j < 5; j++)
                    students.add(new Student(i, j, line.charAt(j)));
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void makeCombination(int idx, int num) {
        if (num == 0) {
            addCombination();
            return;
        }

        if (idx == students.size()) return;

        selected[idx] = true;
        makeCombination(idx + 1, num - 1);

        selected[idx] = false;
        makeCombination(idx + 1, num);
    }

    private static void addCombination() {
        List<Student> comb = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (!selected[i]) continue;
            comb.add(students.get(i));
        }

        if (isDasomWinnable(comb)) combinations.add(comb);
    }

    private static boolean isDasomWinnable(List<Student> set) {
        int dasom = 0, doyeon = 0;
        for (Student student : set) {
            if (student.type == 'Y') doyeon++;
            else dasom++;
        }

        return dasom > doyeon;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    private static int solution() {
        int answer = 0;
        for (List<Student> comb : combinations) {
            Student start = comb.get(0);
            Queue<Student> queue = new LinkedList<>();
            boolean[][] visited = new boolean[5][5];
            visited[start.row][start.col] = true;
            queue.add(start);

            int count = 1;
            while (!queue.isEmpty()) {
                Student student = queue.poll();

                for (int[] move : moves) {
                    int nr = student.row + move[0], nc = student.col + move[1];
                    if (!isValid(nr, nc) || visited[nr][nc]) continue;

                    Student next = students.get(nr * 5 + nc);
                    if (!comb.contains(next)) continue;

                    visited[nr][nc] = true;
                    queue.add(next);
                    count++;
                }
            }

            if (count == 7) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        parseInput();
        makeCombination(0, 7);
        int answer = solution();
        printOutput(answer);
    }
}