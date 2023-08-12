package algoStudy.week1;

import java.util.*;

public class Vacation {
    public static int count = 0;
    public static ArrayList<String> dfs(HashMap<String,Integer> airport, ArrayList<List<String>> graph, String startNode, HashSet<String> visited, ArrayList<String> answer){
        if(visited.size()==graph.size()&&count==0) return answer;
        for (int i = 0; i <graph.get(airport.get(startNode)).size(); i++) {
            String nextNode = graph.get(airport.get(startNode)).get(0);
            graph.get(airport.get(startNode)).remove(0);
            answer.add(nextNode);
            visited.add(nextNode);
            count--;
            ArrayList<String> now = dfs(airport, graph, nextNode, visited, answer);
            if(visited.size()== graph.size()&&count==0) return answer;
            count++;
            visited.remove(nextNode);
            answer.remove(answer.size()-1);
            int j;
            for (j = i; j < graph.get(airport.get(startNode)).size()-1 ; j++) {
                if(!graph.get(airport.get(startNode)).get(i).equals(nextNode)) break;
            }
            System.out.println(graph.get(airport.get(startNode)).size()-1);
            if(j==(graph.get(airport.get(startNode)).size())) graph.get(airport.get(startNode)).add(nextNode);
            else graph.get(airport.get(startNode)).add(j+1,nextNode);
        }
        return answer;
    }
    public ArrayList<String> solution(String[][] tickets) {
        HashMap<String,Integer> airport = new HashMap<>();
        ArrayList<String> answer = new ArrayList<>();
        // 해시 맵에 공항 이름 넣기 (공항별 인덱스 만들기)
        int index = 0;
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                if(airport.get(tickets[i][j])==null) {
                    airport.put(tickets[i][j],index);
                    index++;
                }

            }
        }
        // 그래프 만들기 (우선 순위 큐)
        ArrayList<PriorityQueue<String>> graph = new ArrayList<>();
        for (int i = 0; i< airport.size(); i++) {
            graph.add(new PriorityQueue<>());
        }
        // 그래프 만들기 (우선 순위 큐의 순서 그대로의 리스트)
        ArrayList<List<String>> listGraph = new ArrayList<>();
        for (int i = 0; i< airport.size(); i++) {
            listGraph.add(new ArrayList<>());
        }
        // 그래프 초기화
        for (int i = 0; i < tickets.length; i++) {
            graph.get(airport.get(tickets[i][0])).add(tickets[i][1]);
        }
        // 그래프 초기화
        for (int i = 0; i < graph.size(); i++) {
            int size = graph.get(i).size();
            for (int j = 0; j < size; j++) {
                listGraph.get(i).add(graph.get(i).poll());
            }
        }
        HashSet<String> visited = new HashSet<>();
        answer.add("ICN");
        visited.add("ICN");
        count = tickets.length;
        dfs(airport,listGraph,"ICN",visited,answer);
        return answer;

    }

    public static void main(String[] args) {
        String[][] test = new String[][]{{"ICN","SFO"},{"ICN","ATL"},{"SFO","ATL"},{"ATL","ICN"},{"ATL","SFO"}};
        new Vacation().solution(new String[][]{{"ICN","A"},{"ICN","C"},{"C","D"},{"A","B"},{"A","B"},{"D","ICN"},{"B","A"}});
    }
}
