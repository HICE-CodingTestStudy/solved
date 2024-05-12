package week47.jeongHee;

public class BalloonBomb {
    //https://school.programmers.co.kr/learn/courses/30/lessons/68646?language=java
    //풍선 터트리기
    static int[] left, right;

    static void init(int[] a) {
        int max = Integer.MAX_VALUE;
        left[0] = max;
        for (int i = 1; i < left.length; i++) {
            max = Math.min(max, a[i - 1]);
            left[i - 1] = max;
        }
        max = Integer.MAX_VALUE;
        right[right.length - 1] = max;
        for (int i = right.length - 2; i >= 0; i--) {
            max = Math.min(max, a[i + 1]);
            right[i + 1] = max;
        }
    }

    public int solution(int[] a) {
        left = new int[a.length];
        right = new int[a.length];
        init(a);
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            if(left[i]<a[i] && right[i]<a[i]) continue;
            ans++;
        }
        return ans;
    }

}
