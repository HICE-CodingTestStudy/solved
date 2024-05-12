import java.util.Scanner;

public class Main {
    //https://www.acmicpc.net/problem/14225
    //부분수열의 합
    static int N;
    static int[] arr;
    static boolean[] visited = new boolean[2000000];
    static int min = Integer.MAX_VALUE;

    static void solution(int count, int sum) {
        visited[sum] = true;
        if (count == N) return;
        solution(count + 1, sum + arr[count]);
        solution(count + 1, sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        solution(0, 0);
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) continue;
            System.out.println(i);
            return;
        }
    }
}