package additional;

import java.util.HashMap;
import java.util.Scanner;

public class FashionKing {
    //https://www.acmicpc.net/problem/9375
    //패션왕 신해빈
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            HashMap<String, Integer> hm = new HashMap<>();
            int n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                String name = sc.next();
                String category = sc.next();
                if(hm.get(category)==null)
                    hm.put(category,1);
                else hm.put(category,hm.get(category)+1);
            }
            int ans = 1;
            for (Integer value : hm.values()) {
                ans*=(value+1);
            }
            System.out.println(ans-1);
        }

    }
}
