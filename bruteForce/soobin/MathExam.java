package soobin;

import java.util.ArrayList;
import java.util.List;

public class MathExam {
    public static List<Integer> solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int N = answers.length;
        int[][] students = new int[3][N];
        int[][] strategy = {{1,3,4,5}, {3,1,2,4,5}};
        int[] corrects = { 0, 0, 0 };

        for (int i = 0; i < N; i++) {
            students[0][i] = i % 5 + 1;
            students[1][i] = i % 2 == 0 ? 2: strategy[0][(i - 1) / 2 % 4];
            students[2][i] = i % 2 == 0 ? strategy[1][i / 2 % 5] : students[2][i - 1];
        }

        for (int i = 0; i < N; i++) {
            int correct = answers[i];
            if (students[0][i] == correct) corrects[0]++;
            if (students[1][i] == correct) corrects[1]++;
            if (students[2][i] == correct) corrects[2]++;
        }

        answer.add(1);
        if (corrects[0] < corrects[1] || corrects[0] < corrects[2]) answer.remove(Integer.valueOf(1));
        answer.add(2);
        if (corrects[1] < corrects[0] || corrects[1] < corrects[2]) answer.remove(Integer.valueOf(2));
        answer.add(3);
        if (corrects[2] < corrects[0] || corrects[2] < corrects[0]) answer.remove(Integer.valueOf(3));

        return answer;
    }

    public static void main(String[] args) {
        int[] answers = { 1,2,3,4,5 };
        System.out.println(solution(answers));
    }
}
