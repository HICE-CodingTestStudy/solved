package soobin;

import java.util.*;

public class Process {

    public static int solution(int[] priorities, int location) {
        List<Integer> priorityList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;

        for(int i = 0; i < priorities.length; i++) { priorityList.add(priorities[i]); queue.add(i); }

        while(true) {
            Integer maxPriority = Collections.max(priorityList);
            int current = queue.poll();

            if(priorities[current] == maxPriority) {
                answer++;
                if(current == location) break;
                priorityList.remove(maxPriority);
            } else queue.add(current);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] priorities = {1,1,9,1,1,1};
        int location = 0;
        System.out.println(solution(priorities, location));
    }
}
