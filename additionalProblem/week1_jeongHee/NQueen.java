package com.example.demo.heap;

import java.util.Arrays;
import java.util.Scanner;

public class NQueen {
    //https://www.acmicpc.net/problem/9663
    //NQueen
    static int ans = 0;
    public static void putQueen(int[] queen, int count, int N){
        if(count==N){
            ans++;
            return;
        }
        boolean canPut = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <count; j++) {
                if(queen[j]==i){
                    canPut=false;
                    break;
                }
                if(i-queen[j]==count-j || queen[j]-i==j-count || i-queen[j]==j-count ||  queen[j]-i==count-j){
                    canPut=false;
                    break;
                }
            }
            if(!canPut) {
                canPut=true;
                continue;
            }
            queen[count]=i;
            putQueen(queen,count+1, N);
            queen[count]=-1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] queen = new int[N];
        Arrays.fill(queen,-1);
        putQueen(queen,0,N);
        System.out.println(ans);
    }
}
