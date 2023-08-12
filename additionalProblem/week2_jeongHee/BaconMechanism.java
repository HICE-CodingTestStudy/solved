package additional;

import java.util.*;

public class BaconMechanism {
    //https://www.acmicpc.net/problem/1389
    //케빈 베이컨의 6단계 법칙
    public static int bfs(ArrayList<ArrayList<Integer>> graph, int startNode){
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        int baconNum = 0;
        int count = 0;
        while (!queue.isEmpty()){
            int beforeFriend = visited.size();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                visited.add(node);
                if(beforeFriend!=visited.size()){
                    baconNum+=count;
                    beforeFriend = visited.size();
                }
                for (Integer nextNode : graph.get(node)) {
                    if(visited.contains(nextNode)) continue;
                    queue.add(nextNode);
                }
            }
            count++;

            if(visited.size()==graph.size()-1) return baconNum;
        }
        return baconNum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node = sc.nextInt();
        int edge = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < node+1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edge; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            graph.get(first).add(second);
            graph.get(second).add(first);
        }
        int ans = 1;
        int ansBacon = bfs(graph,1);
        for (int i = 2; i < node+1; i++) {
            int bacon = bfs(graph,i);
            if(bacon<ansBacon){
                ansBacon=bacon;
                ans = i;
            }
        }

        System.out.println(ans);
    }

}
