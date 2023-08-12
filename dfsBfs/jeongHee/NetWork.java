package algoStudy.week1;

import java.util.LinkedList;
import java.util.Queue;

public class NetWork {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43162
    //네트워크
    public static int bfs(int[][] graph, int startNode, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode]=true;
        while (!queue.isEmpty()){
            int nextNode = queue.poll();
            for (int i = 0; i < graph[nextNode].length; i++) {
                if(graph[nextNode][i]!=1) continue;
                if(visited[i]) continue;
                queue.add(i);
                visited[i]=true;
            }
        }
        return -1;
    }
    public int solution(int n, int[][] computers) {
        int index = 0;
        int count = 0;
        boolean[] visited = new boolean[computers.length];
        while (index!=computers.length){
            bfs(computers,index,visited);
            while (index<computers.length&&visited[index]) index++;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        NetWork n = new NetWork();
        n.solution(3,new int[][]{{1,1,0},{1,1,0},{0,0,1}});
    }

}
