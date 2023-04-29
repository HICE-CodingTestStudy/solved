package queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Truck {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42583
    //다리를 지나는 트럭

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> done = new LinkedList<>();
        Deque<Integer> waiting = new LinkedList<>();
        Deque<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        int time = 0;
        int weightInBridge = 0;
        for (int i = 0; i < truck_weights.length; i++) {
            waiting.add(truck_weights[i]);
        }
        while (done.size() != truck_weights.length){
            time++;
            int out = bridge.pollFirst();
            weightInBridge-=out;
            if(out!=0)
                done.addLast(out);
            if(!waiting.isEmpty()&&weightInBridge+waiting.peekFirst()<=weight){
                weightInBridge+=waiting.peekFirst();
                bridge.addLast(waiting.pollFirst());
            } else bridge.addLast(0);
        }
        return time;
    }
}
