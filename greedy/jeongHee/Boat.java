package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Boat {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42885
    //구명 보트

    //시간 초과 버전
//    public int solution(int[] people, int limit) {
//        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
//        PriorityQueue<Integer> min = new PriorityQueue<>();
//        for (int person : people) {
//            max.add(person);
//            min.add(person);
//        }
//        int answer = 0;
//        while (!max.isEmpty()||!min.isEmpty()){
//            if(max.isEmpty()){
//                answer+=min.size();
//                break;
//            }
//            if(min.isEmpty()){
//                answer+=max.size();
//                break;
//            }
//            if(max.peek()+min.peek()<=limit){
//                answer++;
//                int mx = max.poll();
//                int mn = min.poll();
//                max.remove(mn);
//                min.remove(mx);
//                continue;
//            }
//            min.remove(max.poll());
//            answer++;
//        }
//        return answer;
//    }
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int max = people.length-1;
        int min = 0;
        int answer = 0;
        while (max>=min){
            if(people[max]+people[min]<=limit){
                answer++;
                max--;
                min++;
                continue;
            }
            max--;
            answer++;
        }
        return answer;
        }

    public static void main(String[] args) {
        Boat b = new Boat();
        System.out.println(b.solution(new int[]{70,50,80,50},100));
    }
}
