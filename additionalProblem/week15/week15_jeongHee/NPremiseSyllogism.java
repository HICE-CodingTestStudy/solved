package com.example.demo.heap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class NPremiseSyllogism {
    //https://www.acmicpc.net/problem/15723
    //n단 논법
    static HashMap<Character, HashSet<Character>> hm = new HashMap<>();

    public static void updateSet(char first, char second) {
        // a is b 가 새롭게 주어진 경우
        // 기존에 a이던것들에 모두 b를 추가해준다.
        for (Character character : hm.keySet()) {
            HashSet<Character> hs = hm.get(character);
            if (hs.contains(first)) {
                hs.add(second);
                hm.put(character, hs);
            }
        }

        // b is a
        // a is b
        // 의 입력이 주어진 경우
        // a 에 b에 해당하는 모든 것들을 추가해준다.
        HashSet<Character> hs = hm.get(first);
        hs.addAll(hm.get(second));
        hm.put(first, hs);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            char first = sc.next().charAt(0);
            sc.next();
            char second = sc.next().charAt(0);
            if (!hm.containsKey(first))
                hm.put(first, new HashSet<>());
            if (!hm.containsKey(second))
                hm.put(second, new HashSet<>());
            HashSet<Character> hs = hm.get(first);
            hs.add(second);
            hm.put(first, hs);
            updateSet(first, second);
        }
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            char first = sc.next().charAt(0);
            sc.next();
            char second = sc.next().charAt(0);
            if (!hm.containsKey(first)) {
                System.out.println("F");
                continue;
            }
            if (hm.get(first).contains(second)) {
                System.out.println("T");
            } else System.out.println("F");
        }
    }

}
