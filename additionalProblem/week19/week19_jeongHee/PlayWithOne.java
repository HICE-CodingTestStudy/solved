package additional;

import java.util.Scanner;

public class PlayWithOne {
    //https://www.acmicpc.net/problem/1612
    //가지고 노는 1
    static int N;

    static int solution() {
        int oneNum = 1;
        int ans = 1;
        while (true) {
            if (oneNum % N == 0) return ans;
            oneNum = (oneNum % N * 10 + 1) % N;
            ans++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if (N % 5 == 0 || N % 2 == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(solution());
    }
}
