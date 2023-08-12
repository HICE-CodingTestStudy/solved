package greedy;

import java.util.Arrays;

public class GymSuit {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42862
    // 체육복
    // 근데 5번이 없어서 1번이 2개 있을때 1번이 2번주고 2번이 3번주고 해서 하는건 안됨?
    public int solution(int n, int[] lost, int[] reserve) {
        int[] suit = new int[n];
        Arrays.fill(suit,1);
        for (int i : lost) {
            suit[i-1]--;
        }
        for (int i : reserve) {
            suit[i-1]++;
        }
        for (int i = 0; i < suit.length; i++) {
            if(i!=0&&suit[i]>1&&suit[i-1]==0){
                suit[i]--;
                suit[i-1]++;
            }
            if (i!=n-1&&suit[i]>1&&suit[i+1]==0) {
                suit[i]--;
                suit[i+1]++;
            }
        }
        int answer = 0;
        for (int i : suit) {
            if(i!=0) answer++;
        }
        return answer;
    }
}
