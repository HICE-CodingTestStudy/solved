package stack;

public class BestSet {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12938
    //최고의 집합
    public int[] solution(int n, int s) {
        int[] ans = new int[n];
        if (s / n == 0) {
            return new int[]{-1};
        }
        for (int i = 0; i < n - s % n; i++) {
            ans[i] = (s / n);
        }
        for (int i = n - s % n; i < n; i++) {
            ans[i] = (s / n + 1);
        }
        return ans;
    }

}
