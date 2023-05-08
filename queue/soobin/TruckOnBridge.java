package soobin;

import java.util.LinkedList;
import java.util.Queue;

class Truck {
    private int id;
    private int time;

    public Truck(int id, int time) { this.id = id; this.time = time; }
    public int getId() { return id; }
    public int getTime() { return time; }
    public void increaseTime() { time++; }
}

public class TruckOnBridge {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> wating = new LinkedList<>();
        Queue<Truck> onBridge = new LinkedList<>();
        int answer = 0;
        int currentWeight = 0;

        for(int i = 0; i < truck_weights.length; i++) wating.add(i);

        while(!wating.isEmpty() || !onBridge.isEmpty()) {
            answer++;

            if(!onBridge.isEmpty()) {
                if(onBridge.peek().getTime() == bridge_length) {
                    Truck passed = onBridge.poll();
                    currentWeight -= truck_weights[passed.getId()];
                }
                for(Truck truck : onBridge) truck.increaseTime();
            }

            if(!wating.isEmpty()) {
                int nextWeight = truck_weights[wating.peek()];
                if(onBridge.size() >= bridge_length || currentWeight + nextWeight > weight) continue;

                onBridge.add(new Truck(wating.poll(), 1));
                currentWeight += nextWeight;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int bride_length = 100;
        int weight = 100;
        int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};

        System.out.println(solution(bride_length, weight, truck_weights));
    }
}
