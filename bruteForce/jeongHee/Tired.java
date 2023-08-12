package bruteFroce;

import java.util.Collections;
import java.util.PriorityQueue;

public class Tired {
    //https://school.programmers.co.kr/learn/courses/30/lessons/87946
    //피로도
    public void selectDungeons(int hp, boolean[] visited, int[][]dungeons, int max, PriorityQueue<Integer> maxVisited){
        if(hp<=0){
            maxVisited.add(max);
            return;
        }
        for (int i = 0; i < dungeons.length; i++) {
            if(!visited[i]&&hp>=dungeons[i][0]){
                visited[i]=true;
                hp-=dungeons[i][1];
                selectDungeons(hp,visited,dungeons,max+1,maxVisited);
                hp+=dungeons[i][1];
                visited[i]=false;
            }
        }
        maxVisited.add(max);
    }
    public int solution(int k, int[][] dungeons) {
        PriorityQueue<Integer> maxVisited = new PriorityQueue<>(Collections.reverseOrder());
        boolean[] visited = new boolean[dungeons.length];
        selectDungeons(k,visited,dungeons,0,maxVisited);
        return maxVisited.poll();
    }

    public static void main(String[] args) {
        Tired t = new Tired();
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        t.solution(80,dungeons);
    }

}
