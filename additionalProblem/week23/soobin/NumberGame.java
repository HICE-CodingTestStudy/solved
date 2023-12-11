package week23.soobin;

import java.util.Arrays;

public class NumberGame {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int minIdx = 0;
        for (int i = 0; i < A.length; i++)
            for (int j = minIdx; j < B.length; j++)
                if (A[i] < B[j]) {
                    answer++;
                    minIdx = j + 1;
                    break;
                }

        return answer;
    }

    public static void main(String[] args) {
        NumberGame n = new NumberGame();
        int[] A = new int[] {5,1,3,7};
        int[] B = new int[] {2,2,6,8};
        System.out.println(n.solution(A, B));
    }
}
