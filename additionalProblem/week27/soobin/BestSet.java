package week27.soobin;

public class BestSet {
    // 원소들 간의 차이가 최소여야 함
    public int[] solution(int n, int s) {
        if (s / n == 0) return new int[] {-1};

        // n 개의 원소를 동일한 값으로 나누기 (s / n)
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) answer[i] = s / n;

        // s에서 부족한 수 만큼만 채워주기 (오름차순이니까 뒤에서부터)
        int remain = s - (s / n) * n;
        for (int i = 0; i < remain; i++) answer[n - 1 - i]++;

        return answer;
    }
}
