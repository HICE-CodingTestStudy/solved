package additional2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class NumberGame {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12987
    //숫자게임
    static PriorityQueue<Integer> a = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> b = new PriorityQueue<>(Collections.reverseOrder());

    public int solution(int[] A, int[] B) {
        for (int i : A) {
            a.add(i);
        }
        for (int i : B) {
            b.add(i);
        }
        int ans = 0;
        while (!b.isEmpty() && !a.isEmpty()){
            if(b.peek()> a.peek()){
                ans++;
                b.poll();
                a.poll();
                continue;
            }
            if(b.peek()<=a.peek()){
                a.poll();
            }
        }
        return ans;
    }
}
