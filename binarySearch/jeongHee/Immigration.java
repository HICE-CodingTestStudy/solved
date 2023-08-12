package binarySearch.jeongHee;

import java.util.Arrays;

public class Immigration {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43238
    //입국심사
    public long solution(int n, int[] times) {
        long[] endTimes = new long[n*times.length];
        int index = 0;
        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < n; j++) {
                endTimes[index++]=times[i]*(j+1);
            }
        }
        Arrays.sort(endTimes);
        return endTimes[Integer.parseInt(String.valueOf(n))-1];
    }

    public static void main(String[] args) {
        Immigration i = new Immigration();
        System.out.println(i.solution(6,new int[]{7,10}));
    }
}
