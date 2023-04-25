package soobin;

import java.util.*;

class Progress {
    private int idx;
    private int progress;

    public Progress(int idx, int progress) { this.idx = idx; this.progress = progress; }
    public int getIdx() { return idx; }
    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress += progress; }
}

public class FuncDev {
    public static List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Progress> queue = new LinkedList<>();

        for(int i = 0; i < progresses.length; i++) queue.add(new Progress(i, progresses[i]));

        while(!queue.isEmpty()) {
            int current = 0;

            for(int i = 0; i < queue.size(); i++) {
                Progress p = queue.poll();
                int tmpIdx = p.getIdx();
                p.setProgress(speeds[tmpIdx]);
                queue.add(p);
            }

            while(!queue.isEmpty() && queue.peek().getProgress() >= 100) {
                queue.poll();
                current++;
            }

            if(current > 0) answer.add(current);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        System.out.println(solution(progresses, speeds));
    }
}
