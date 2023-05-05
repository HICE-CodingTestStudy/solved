package com.example.demo.heap;

import java.util.PriorityQueue;

public class MoreHot {
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> scov = new PriorityQueue<>();
        for (int sc : scoville) {
            scov.add(sc);
        }
        while (scov.peek()<K){
            if(scov.size()==1)
                return -1;
            scov.add(scov.poll()+scov.poll()*2);
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(MoreHot.solution(new int[]{1, 2, 3, 9, 10, 12},7));
    }

}
