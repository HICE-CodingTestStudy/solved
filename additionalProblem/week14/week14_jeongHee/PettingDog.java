package com.example.demo.heap;

import java.util.Scanner;

public class PettingDog {
    //https://www.acmicpc.net/problem/1669
    //멍멍이 쓰다듬기

    /*
    *
    * 1 2 1 일때
    * 가장 크게 키운 키 = 2, 합 = 4
    *
    * 1 2 3 2 1 일때
    * 가장 크게 키운 키 = 3, 합 = 9
    *
    * 따라서 위와 같이 피라미드 형식으로 키를 키운다고 할때
    * 가장 크게 키운 키가 n 일때 합은 n*n 이다.
    *
    * 따라서 키 차이가 어느 구간에 속하는지 확인 후 남은 수만큼은 적절히 붙이면 된다
    *
    * ex) 키차이가 6일때
    *       1 2(+2) 1
    *
    *
    * */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long monkey = sc.nextLong();
        long dog = sc.nextLong();
        long diff = dog - monkey;
        if (diff == 0) {
            System.out.println(0);
            return;
        }
        long minIndex = 0;
        long maxIndex = 1 << 16;
        long mid;
        while (minIndex <= maxIndex) {
            mid = (minIndex + maxIndex) / 2;
            if (mid * mid < diff) {
                minIndex = mid + 1;
            } else if (mid * mid > diff)
                maxIndex = mid - 1;
            else {
                System.out.println(mid * 2 - 1);
                return;
            }
        }
        mid = (minIndex + maxIndex) / 2;
        long left = diff - mid * mid;
        long q = left / mid;
        long r = left % mid;
        if (r != 0) r = 1;
        System.out.println(mid * 2 - 1 + q + r);
    }
}
