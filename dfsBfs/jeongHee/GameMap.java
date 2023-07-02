package algoStudy.week1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class GameMap {
    //https://school.programmers.co.kr/learn/courses/30/lessons/1844
    //게입 맵 최단 거리
    public class Coordinate{
        private int x;
        private int y;
        private int distance;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDistance() {
            return distance;
        }
        public Coordinate(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public int solution(int[][] maps) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0,0, 1));
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        visited[0][0]=true;
        while (!queue.isEmpty()){
            Coordinate next = queue.poll();
            int x = next.getX();
            int y = next.getY();
            if((x==maps.length-1)&&(y==maps[0].length-1))
                return next.getDistance();
            if(y-1>=0&&maps[x][y-1]==1&&!visited[x][y-1]){
                queue.add(new Coordinate(x,y-1, next.getDistance()+1));
                visited[x][y-1]=true;
            }
            if(y+1<maps[0].length&&maps[x][y+1]==1&&!visited[x][y+1]){
                queue.add(new Coordinate(x,y+1, next.getDistance()+1));
                visited[x][y+1]=true;
            }
            if(x-1>=0&&maps[x-1][y]==1&&!visited[x-1][y]){
                queue.add(new Coordinate(x-1,y, next.getDistance()+1));
                visited[x-1][y]=true;
            }
            if(x+1<maps.length&&maps[x+1][y]==1&&!visited[x+1][y]){
                queue.add(new Coordinate(x+1,y, next.getDistance()+1));
                visited[x+1][y]=true;
            }
        }
        return -1;

    }
    // 아래는 틀린 풀이
    // 1. dfs -> 오답 가능, 시간 초과
    // 2. bfs를 그래프를 우선 생성 후 돌림 -> 시간 초과

//    public static int dfs(ArrayList<ArrayList<Integer>> graph, int startNode, boolean[] visited, int target, int count){
//        if(visited[target])
//            return count;
//        for (int i = 0; i < graph.get(startNode).size(); i++) {
//            visited[startNode]=true;
//            int nextNode = graph.get(startNode).get(i);
//            if(!visited[nextNode]) {
//                visited[nextNode] = true;
//                int ret = dfs(graph, nextNode, visited,target, count + 1);
//                if (ret != -1) return ret;
//                visited[nextNode] = false;
//            }
//        }
//        return -1;
//    }
//    public static int bfs(ArrayList<ArrayList<Integer>> graph, int startNode, boolean[] visited, int[] distance, int target){
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(startNode);
//        visited[startNode]=true;
//        distance[0]=1;
//        while (!queue.isEmpty()){
//            int nextNode = queue.poll();
//            System.out.println(nextNode);
//            if(nextNode==target) return distance[target];
//            for (int adjNode : graph.get(nextNode)) {
//                if(visited[adjNode]) continue;
//                queue.add(adjNode);
//                visited[adjNode]=true;
//                distance[adjNode]=distance[nextNode]+1;
//            }
//        }
//        return -1;
//    }
//    public static int solution(int[][] maps) {
//        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
//        for (int i = 0; i < maps.length*maps[0].length; i++) {
//            graph.add(new ArrayList<>());
//        }
//        for (int i = 0; i < maps.length; i++) {
//            for (int j = 0; j < maps[i].length; j++) {
//                int now = i*maps[0].length+j;
//                if(maps[i][j]!=0){
//                    //왼쪽
//                    if(j-1>=0&&maps[i][j-1]==1)
//                        graph.get(now).add(now-1);
//                    //오른쪽
//                    if(j+1<maps[i].length&&maps[i][j+1]==1)
//                        graph.get(now).add(now+1);
//                    //위
//                    if(i-1>=0&&maps[i-1][j]==1)
//                        graph.get(now).add(now-maps[0].length);
//                    //아래
//                    if(i+1<maps.length&&maps[i+1][j]==1)
//                        graph.get(now).add(now+maps[0].length);
//                }
//            }
//        }
//        boolean[] visited = new boolean[maps.length*maps[0].length];
//        //return dfs(graph,0,visited, maps.length*maps[0].length-1, 1);
//        return bfs(graph,0,visited,new int[maps.length*maps[0].length] ,maps.length*maps[0].length-1);
//    }

    public static void main(String[] args) {
        System.out.println(new GameMap().solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
    }
}
