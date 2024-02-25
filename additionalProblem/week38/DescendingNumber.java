package queue;

import java.util.*;

public class DescendingNumber {
    static int N;
    static int[] dp = new int[10];
    static int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static List<Long> list = new ArrayList<>();

    static long makeNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (dp[i] == 0 && sb.toString().equals("")) continue;
            sb.append(dp[i]);
        }
        if (sb.toString().equals("")) return 0;
        return Long.parseLong(sb.toString());
    }

    static void solution(int count, int digit, int index) {
        if (count == digit) {
            list.add(makeNumber());
            return;
        }
        for (int i = index; i < arr.length; i++) {
            dp[count] = arr[i];
            solution(count + 1, digit, i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < 10; i++) {
            solution(0, i + 1, 0);
        }
        Collections.sort(list);
        if(N<list.size()) System.out.println(list.get(N));
        else System.out.println(-1);
    }
}
