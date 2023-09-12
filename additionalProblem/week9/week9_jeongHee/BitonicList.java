package queue;

import java.util.Scanner;

public class BitonicList {
    //https://www.acmicpc.net/problem/11054
    //가장 긴 바이토닉 부분 수열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] up = new int[N];
        int[] down = new int[N];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        up[0] = 1;
        for (int i = 1; i < N; i++) {
            int biggest = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    biggest = Math.max(biggest,up[j]);
                }
            }
            up[i]=biggest+1;
        }
        down[N-1] = 1;
        for (int i = N-2; i >= 0; i--) {
            int smallest = 0;
            for (int j = N-1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    smallest = Math.max(smallest,down[j]);
                }
            }
            down[i]=smallest+1;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans,down[i]+up[i]);
        }
        System.out.println(ans-1);
    }
}
