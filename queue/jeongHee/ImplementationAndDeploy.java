package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ImplementationAndDeploy {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42586
    //기능 개발
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        Queue<Integer> program = new LinkedList<>();
        int totalFinished = 0;
        for (int progress : progresses) {
            program.add(progress);
        }
        while (!program.isEmpty()){
            for (int i = totalFinished; i <speeds.length ; i++) {
                program.add(program.poll()+speeds[i]);
            }
            int deployThisTime = 0;
            while (!program.isEmpty()&&program.peek()>=100){
                program.poll();
                deployThisTime++;
            }
            if(deployThisTime!=0)
                answer.add(deployThisTime);
            totalFinished+=deployThisTime;
        }
        return answer;
    }

}
