package soobin;

public class TargetNumber {
    private int answer = 0;
    private void DFS(boolean isPositive, int[] numbers, int i, int sum, int target) {
        if (isPositive) sum += numbers[i];
        else sum -= numbers[i];

        if (i == numbers.length - 1) {
            if (sum == target) answer++;
            return;
        }

        DFS(true, numbers, i + 1, sum, target);
        DFS(false, numbers, i + 1, sum, target);
    }

    public int solution(int[] numbers, int target) {
        answer = 0;
        DFS(true, numbers, 0, 0, target);
        DFS(false, numbers, 0, 0, target);
        return answer;
    }

    public static void main(String[] args) {
        TargetNumber tn = new TargetNumber();
        System.out.println(tn.solution(new int[]{1,1,1,1,1}, 3));
        System.out.println(tn.solution(new int[]{4, 1, 2, 1}, 4));
    }
}
