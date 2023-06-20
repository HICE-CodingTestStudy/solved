package algoStudy.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Virus {
    public static void bfs(HashMap<Integer,Integer> hm, ArrayList<ArrayList<Integer>> graph, int index){
        hm.put(index,1);
        for (int i = 0; i < graph.get(index).size(); i++) {
            if(hm.get(graph.get(index).get(i))!=null)
                continue;
            hm.put(graph.get(index).get(i),1);
            bfs(hm,graph,graph.get(index).get(i));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();
        int N = sc.nextInt();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        int M = sc.nextInt();
        for (int i = 1; i <= M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        hm.put(1,1);
        bfs(hm,graph,1);
        System.out.println(hm.size()-1);
    }
}
