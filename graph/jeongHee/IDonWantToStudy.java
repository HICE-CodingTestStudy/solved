package algoStudy.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class IDonWantToStudy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int ans = 0;
        int max = 0;
        int N = sc.nextInt();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            graph.get(i).add(sc.nextInt());
        }
        for (int i = 1; i <= N ; i++) {
            HashMap<Integer,Integer> hm = new HashMap<>();
            int next = i;
            int count = 0;
            while (true){
                if(hm.get(next)!=null)
                    break;
                hm.put(next,1);
                next = graph.get(next).get(0);
                count++;
            }
            if(count>max){
                max = count;
                ans = i;
            }
        }
        System.out.println(ans);

    }
}
