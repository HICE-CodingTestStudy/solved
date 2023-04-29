package queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Process {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42587
    //프로세스
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<int[]> queue = new LinkedList<>();
        int answer=1;
        for (int i = 0; i <priorities.length ; i++) {
            int[] x = new int[2];
            x[0]=i;
            x[1]=priorities[i];
            queue.offer(x);
            pq.offer(priorities[i]);
        }
        while (!queue.isEmpty()&&!pq.isEmpty()){
            if(priorities[location]==pq.peek()&&location==queue.peek()[0]){
                break;
            }
            if(queue.peek()[1]==pq.peek()){
                queue.poll();
                pq.poll();
                answer++;
            }else {
                queue.offer(queue.poll());
            }
        }
        return answer;
    }
}