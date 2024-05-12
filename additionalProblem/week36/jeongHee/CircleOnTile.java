package queue;


import java.util.Scanner;

public class CircleOnTile {
    //https://www.acmicpc.net/problem/1709
    //타일 위의 원
    static int N;
    static long radius;

    static long getDistance(int i1, int j1, int i2, int j2) {
        long i = Math.abs(i1 - i2);
        long j = Math.abs(j1 - j2);
        return i * i + j * j;
    }

    static boolean isDrawn(int ldI, int ldJ, int ruI, int ruJ) {
        long radiusPow = radius * radius;
        return getDistance(0, 0, ldI, ldJ) < radiusPow
                && getDistance(0, 0, ruI, ruJ) > radiusPow;
    }

    static void solution() {
        long ans = 0;
        int i = 0;
        int j = N - 1;
        while (true) {
            if (i >= N || j < 0) break;
            if (isDrawn(i, j, i + 1, j + 1)) {
                ans++;
                i++;
                continue;
            }
            i--;
            j--;
            if (isDrawn(i, j, i + 1, j + 1)) {
                ans++;
            }
            i++;
        }
        System.out.println(ans * 4);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        radius = N / 2;
        solution();
    }
}
