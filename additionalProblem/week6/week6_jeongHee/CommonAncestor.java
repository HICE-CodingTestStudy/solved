package queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CommonAncestor {
    //https://www.acmicpc.net/problem/3584
    //가장 가까운 공통 조상
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            HashMap<Integer, Integer> hm = new HashMap<>();
            for (int j = 0; j < N - 1; j++) {
                int root = sc.nextInt();
                int sibling = sc.nextInt();
                hm.put(sibling, root);
            }
            int a = sc.nextInt();
            int b = sc.nextInt();
            HashSet<Integer> root = new HashSet<>();
            root.add(a);
            root.add(b);
            while (true) {
                if(hm.get(a)!=null){
                    a = hm.get(a);
                    if(root.contains(a)){
                        System.out.println(a);
                        break;
                    }
                    root.add(a);
                }
                if(hm.get(b)!=null){
                    b = hm.get(b);
                    if(root.contains(b)){
                        System.out.println(b);
                        break;
                    }
                    root.add(b);
                }
            }
        }
    }
}
