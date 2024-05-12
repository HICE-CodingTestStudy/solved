package queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class DefenseGame {
    //https://school.programmers.co.kr/learn/courses/30/lessons/142085
    //디펜스 게임

    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        for (int i = 0; i < enemy.length; i++) {
            if (sum+enemy[i]<=n){
                pq.add(enemy[i]);
                sum+=enemy[i];
                continue;
            }
            if (k==0) {
                return pq.size();
            }
            if (k>0){
                pq.add(enemy[i]);
                sum+=enemy[i];
                sum-=pq.poll();
                pq.add(0);
                k--;
            }
        }
        return pq.size();
    }

    public static void main(String[] args) {
        DefenseGame d = new DefenseGame();
        d.solution(7,3,new int[]{4,2,4,5,3,3,1});
    }
}
