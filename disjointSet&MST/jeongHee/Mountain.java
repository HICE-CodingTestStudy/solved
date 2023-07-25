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

    public static class Node{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    public static int dijkstra(int N, int source, int dest, List<Node>[] graph, boolean[] isTop, boolean[] isGate){
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1->o1.weight)
        );
        int[] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Node(source,0));
        while (!pq.isEmpty()){
            Node u = pq.poll();
            for (Node v : graph[u.index]) {
                if(dist[v.index]<=dist[u.index]) continue;
                if(isTop[v.index]) continue;
                dist[v.index] = Math.max(dist[u.index],v.weight);
                pq.add(new Node(v.index, dist[v.index]));
            }
        }
        return dist[dest];
    }
//    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
//        List<Node>[] graph = new List[n+1];
//        boolean[] isGate = new boolean[n+1];
//        boolean[] isTop = new boolean[n+1];
//        for (int gate : gates) {
//            isGate[gate]=true;
//        }
//        for (int summit : summits) {
//            isTop[summit]=true;
//        }
//        for (int i = 0; i < n+1; i++) {
//            graph[i]=new ArrayList<>();
//        }
//        for (int[] path : paths) {
//            graph[path[0]].add(new Node(path[1], path[2]));
//            graph[path[1]].add(new Node(path[0], path[2]));
//        }
//        int top = -1;
//        int intensity = Integer.MAX_VALUE;
//        for (int summit : summits) {
//            int ret = dijkstra(n,summit,graph,isTop,isGate);
//            if(ret<intensity) {
//                intensity = ret;
//                top = summit;
//            }
//        }
//        int[] ans = new int[2];
//        ans[0]=top;
//        ans[1]=intensity;
//        return ans;
//    }
//

    public static class Graph implements Comparable<Graph>{
        int a;
        int b;
        int cost;

        public Graph(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Graph o) {
            if(this.cost>o.cost) return 1;
            if(this.cost<o.cost) return -1;
            else return 0;
        }
    }
    public static int find(int a, int[] graph){
        if(graph[a]==-1) return a;
        return graph[a]=find(graph[a],graph);
    }
    public static boolean union(int a, int b, int[] graph, int[] height){
        int rootA = find(a,graph);
        int rootB = find(b,graph);
        if(rootB==rootA) return false;
        if(rootA>=rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        if(height[rootA]==height[rootB]){
            graph[rootA]=rootB;
            height[rootA]=-1;
            height[rootB]++;
            return true;
        }
        graph[rootA]=rootB;
        height[rootA]=-1;
        return true;
    }
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] graph = new int[n + 1];
        int[] height = new int[n + 1];
        boolean[] isGate = new boolean[n + 1];
        boolean[] isTop = new boolean[n + 1];
        int[] answer = new int[2];
        int maxCost = -1;
        for (int gate : gates) {
            isGate[gate] = true;
        }
        for (int summit : summits) {
            isTop[summit] = true;
        }
        Arrays.fill(graph, -1);
        PriorityQueue<Graph> pq = new PriorityQueue<>();
        for (int i = 0; i < paths.length; i++) {
            pq.add(new Graph(paths[i][0], paths[i][1], paths[i][2]));
        }
        ArrayList<Integer> includedTop = new ArrayList<>();
        ArrayList<Integer> includedStart = new ArrayList<>();
        List<Node>[] listGraph = new List[n+1];
        for (int gate : gates) {
            isGate[gate]=true;
        }
        for (int summit : summits) {
            isTop[summit]=true;
        }
        for (int i = 0; i < n+1; i++) {
            listGraph[i]=new ArrayList<>();
        }
        for (int[] path : paths) {
            listGraph[path[0]].add(new Node(path[1], path[2]));
            listGraph[path[1]].add(new Node(path[0], path[2]));
        }
        while (!pq.isEmpty()) {
            Graph g = pq.poll();
            union(g.a, g.b, graph, height);
            maxCost = Math.max(g.cost, maxCost);
            if (isTop[g.a]) includedTop.add(g.a);
            if (isTop[g.b]) includedTop.add(g.b);
            if (isGate[g.a]) includedStart.add(g.a);
            if (isGate[g.b]) includedStart.add(g.b);
            if(pq.size()>=1&&pq.peek().cost==g.cost) continue;
            if(!isTop[g.a]&&!isTop[g.b]&&!isGate[g.a]&&!isGate[g.b]) continue;
            Collections.sort(includedTop);
            for(int summit : includedTop) {
                for (int gate : includedStart) {
                    if (find(gate, graph) == find(summit, graph)) {
                        if(dijkstra(n,summit,gate,listGraph,isTop,isGate)==maxCost) {
                            answer[0] = summit;
                            answer[1] = maxCost;
                            return answer;
                        }
                    }
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        solution(	7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2, 3, 4});
        //solution(	7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6,7,1}}, new int[]{3,7}, new int[]{1,5});

    }
}
