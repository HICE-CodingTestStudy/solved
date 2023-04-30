package soobin;

import java.util.PriorityQueue;

public class MoreSpicy {
    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int answer = 0;

        for(int n : scoville) queue.add(n);

        while(queue.peek() < K) {
            int first = queue.poll();
            if(queue.isEmpty()) {
                answer = -1; break;
            }
            int second = queue.poll();
            queue.add(first + second * 2);
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(solution(scoville, K));
    }
}
