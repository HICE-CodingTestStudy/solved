package queue;

import java.util.Scanner;

public class SurfaceArea {
    //https://www.acmicpc.net/problem/16931
    //겉넓이 구하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int ans = N * M * 2;
        int[][] block = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                block[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < N; i++) {
            int tmp = block[i][0];
            ans += 2 * tmp;
            for (int j = 1; j < M; j++) {
                if (tmp < block[i][j]) {
                    ans += (block[i][j] - tmp) * 2;
                }
                tmp = block[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            int tmp = block[0][i];
            ans += 2 * tmp;
            for (int j = 1; j < N; j++) {
                if (tmp < block[j][i]) {
                    ans += (block[j][i] - tmp) * 2;
                }
                tmp = block[j][i];
            }
        }
        System.out.println(ans);
    }
}
