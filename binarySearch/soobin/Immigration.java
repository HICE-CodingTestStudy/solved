package soobin;

import java.util.*;

public class Immigration {
    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long low = 1, high = (long) times[times.length - 1] * (long) n;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = 0;
            for (int i = 0; i < times.length; i++) count += mid / times[i];

            if (count >= n) high = mid;
            else low = mid + 1;
        }
        return high;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7,10};
        System.out.println(solution(n, times));
    }
}
