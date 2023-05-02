package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueue2 {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42628
    //이중 우선순위 큐

    public ArrayList<Integer> solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

        boolean isMin = false;
        for (String operation : operations) {
            String[] result = operation.split(" ");
            if (result[0].equals("I")) {
                if (isMin) {
                    min.add(Integer.parseInt(result[1]));
                } else {
                    max.add(Integer.parseInt(result[1]));
                }
                continue;
            }
            if(Integer.parseInt(result[1])==-1){
                if(isMin&&!min.isEmpty()){
                    min.poll();
                } else if(!isMin) {
                    while (!max.isEmpty()){
                        min.add(max.poll());
                        isMin = true;
                    }
                    if(!min.isEmpty()) min.poll();
                }
            }

            if(Integer.parseInt(result[1])==1){
                if(!isMin&&!max.isEmpty()){
                    max.poll();
                } else if(isMin){
                    while (!min.isEmpty()){
                        max.add(min.poll());
                        isMin = false;
                    }
                    if(!max.isEmpty()) max.poll();
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        if(max.isEmpty()&&min.isEmpty()){
            ans.add(0);
            ans.add(0);
            return ans;
        }

        if(isMin){
            while (!min.isEmpty()){
                max.add(min.poll());
                isMin = false;
            }
        }
        ans.add(max.peek());
        int minAns = max.poll();
        while (!max.isEmpty()){
            minAns=max.poll();
        }
        ans.add(minAns);
        return ans;

    }

    public static void main(String[] args) {
        String[] st = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        PriorityQueue2 priorityQueue2 = new PriorityQueue2();
        System.out.println(priorityQueue2.solution(st));
    }
}
