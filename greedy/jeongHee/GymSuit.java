package greedy;

import java.util.Arrays;

public class GymSuit {
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
