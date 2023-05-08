package soobin;

import java.util.PriorityQueue;

public class DiskController {
    public static int solution(int[][] jobs) {
        int N = jobs.length;
        int[] totalTimes = new int[N];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] < o2[0] ? -1: 1);
        PriorityQueue<int[]> waiting = new PriorityQueue<>((o1, o2) -> o1[1] < o2[1] ? -1 : 1);
        for(int[] job : jobs) queue.add(job);

        int i = 0;
        int current = 0;
        int running = 0;
        int[] currentJob = null;
        while(true) {
            while (!queue.isEmpty() && current == queue.peek()[0]) waiting.add(queue.poll());

            if (currentJob != null && currentJob[1] == running) {
                currentJob = null;
            }

            if (currentJob == null && !waiting.isEmpty()) {
                int[] job = waiting.poll();
                totalTimes[i++] = (current - job[0]) + job[1];
                currentJob = job;
                running = 0;
            }

            if(waiting.isEmpty() && queue.isEmpty()) break;
            current++;
            running++;
        }

        int sum = 0;
        for(int totalTime : totalTimes) sum += totalTime;
        return sum / N;
    }

    public static void main(String[] args) {
        int[][] jobs = {{0,3},{1,9},{2,6}};
        System.out.println(solution(jobs));
    }
}
