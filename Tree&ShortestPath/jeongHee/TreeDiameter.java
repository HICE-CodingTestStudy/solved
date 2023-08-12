package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeDiameter {
    //https://www.acmicpc.net/problem/1967
    //트리의 지름
    static int max = -1;
    public static class Node{
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    public static int dijkstra(int N, int source, List<Node>[] graph){
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
                if(dist[v.index]<=dist[u.index]+v.weight) continue;
                dist[v.index] = dist[u.index] + v.weight;
                pq.add(new Node(v.index, dist[v.index]));
            }
        }
        int ansIndex = -1;
        for (int i = 1; i < N+1 ; i++) {
            if(dist[i]>max&&dist[i]!=Integer.MAX_VALUE){
                max=dist[i];
                ansIndex=i;
            }
        }
        return ansIndex;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int isRoot = 0;
        List<Node>[] graph = new List[N+1];
        for (int i = 0; i < N+1; i++) {
            graph[i]=new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st=new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int sibling = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[root].add(new Node(sibling, weight));
            graph[sibling].add(new Node(root, weight));
            isRoot=Math.max(isRoot,root);
        }
        int startNode = dijkstra(N,1,graph);
        dijkstra(N,startNode,graph);
        System.out.println(max);
    }


    //시간 초과
//    static int max = -1;
//    public static class Node{
//        int index;
//        int weight;
//
//        public Node(int index, int weight) {
//            this.index = index;
//            this.weight = weight;
//        }
//    }
//
//    public static int dfs(List<Node>[] graph, int startNode, boolean[] visited, int target, int cost){
//        if(visited[target])
//            return cost;
//        for (int i = 0; i < graph[startNode].size(); i++) {
//            visited[startNode]=true;
//            Node nextNode = graph[startNode].get(i);
//            if(!visited[nextNode.index]) {
//                visited[nextNode.index] = true;
//                int ret = dfs(graph, nextNode.index, visited,target, cost + nextNode.weight);
//                visited[nextNode.index] = false;
//                if (ret != -1) return ret;
//            }
//        }
//        return -1;
//    }
//
//    public static void solution(int N, List<Node>[] graph, int isRoot) {
//        boolean[] visited = new boolean[N+1];
//        for (int i = isRoot+1; i < N+1; i++) {
//            for (int j = i+1; j < N+1; j++) {
//                max=Math.max(dfs(graph,i,visited,j,0),max);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int isRoot = 0;
//        List<Node>[] graph = new List[N+1];
//        for (int i = 0; i < N+1; i++) {
//            graph[i]=new ArrayList<>();
//        }
//        for (int i = 0; i < N-1; i++) {
//            st=new StringTokenizer(br.readLine());
//            int root = Integer.parseInt(st.nextToken());
//            int sibling = Integer.parseInt(st.nextToken());
//            int weight = Integer.parseInt(st.nextToken());
//            graph[root].add(new Node(sibling, weight));
//            graph[sibling].add(new Node(root, weight));
//            isRoot=Math.max(isRoot,root);
//        }
//        solution(N,graph,isRoot);
//        System.out.println(max);
//    }
}
