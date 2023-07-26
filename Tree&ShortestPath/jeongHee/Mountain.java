package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mountain {
    //https://school.programmers.co.kr/learn/courses/30/lessons/118669
    //등산 코스 정하기
    /*
    * 1. 최소신장 트리
    * 게이트와 출발지가 한 트리로 이어지는 순간 최대 가중치와 최소 봉우리 출력
    * ->봉우리를 두번 거치면 안된다는 조건 충족 x
    * 2. 다익스트라
    * 최단거리를 갱신해가며(이제까지 걸어온 길들의 가중치들 중 최댓값) 저장
    * 이때 source는 특정 봉우리이라서 다른 봉우리를 거치는 길은 무효처리해야하기때문에
    * 다른 봉우리는 큐에 다시 넣지 않음
    * -> 시간 초과, 틀림 왜 틀린지는 모르겠음
    * 3. 1+2
    * 최소신장 트리를 만들면서 봉우리를 두번 거치지 않는걸 확인하기 위해 다익스트라를 합침
    * -> 시간 초과, 실패
    * */

    static PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingInt(o1->o1.weight)
    );

    public static class Node{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    public static int[] dijkstra(int[] dist, List<Node>[] graph, boolean[] isTop){
        int min = Integer.MAX_VALUE;
        int index = -1;
        while (!pq.isEmpty()){
            Node u = pq.poll();
            if(isTop[u.index]) continue;
            if(u.weight>=min) continue;
            //꺼낸 점의 가중치 자체가 기존 min보다 크면 이걸 살펴볼 필요가 없음
             for (Node v : graph[u.index]) {
                if(dist[v.index]<=Math.max(dist[u.index],v.weight)) continue;
                dist[v.index] = Math.max(dist[u.index],v.weight);
                if(isTop[v.index]) {
                    if(min<dist[v.index]) continue;
                    if(min==dist[v.index]){
                        index=Math.min(index, v.index);
                        continue;
                    }
                    min = Math.min(min,dist[v.index]);
                    index=v.index;
                    continue;
                }
                pq.add(new Node(v.index, dist[v.index]));

            }
        }
        return new int[]{index,min};
    }
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Node>[] graph = new List[n+1];
        boolean[] isGate = new boolean[n+1];
        boolean[] isTop = new boolean[n+1];
        int[] dist = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            graph[i]=new ArrayList<>();
            dist[i]=Integer.MAX_VALUE;
        }
        for (int gate : gates) {
            isGate[gate]=true;
            dist[gate]=0;
            pq.add(new Node(gate,0));
        }
        for (int summit : summits) {
            isTop[summit]=true;
        }
        for (int[] path : paths) {
            if(isTop[path[0]]||isGate[path[1]]){
                graph[path[1]].add(new Node(path[0], path[2]));
                continue;
            }
            if(isTop[path[1]]||isGate[path[0]]){
                graph[path[0]].add(new Node(path[1], path[2]));
                continue;
            }
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }
        int top = -1;
        int intensity = Integer.MAX_VALUE;
        int[] ret = dijkstra(dist,graph,isTop);
        return ret;
    }


    public static void main(String[] args) {
       // solution(	7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2, 3, 4});
        //solution(	7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6,7,1}}, new int[]{3,7}, new int[]{1,5});
        solution(	6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3},{4,6,1},{5,6,1}}, new int[]{1,3}, new int[]{5});

    }
}
