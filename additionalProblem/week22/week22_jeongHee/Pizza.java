package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class Pizza {
    //https://www.acmicpc.net/problem/2632
    //피자 판매
    static int target, n, m;
    static List<Integer> a = new ArrayList<>();
    static List<Integer> b = new ArrayList<>();
    static Map<Integer, Integer> aMap = new HashMap<>();
    static Map<Integer, Integer> bMap = new HashMap<>();

    static void makePiece(Map<Integer, Integer> map, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int sum = 0;
            int index = i;
            for (int j = 0; j < list.size() - 1; j++) {
                sum += list.get((index++) % list.size());
                map.putIfAbsent(sum, 0);
                map.put(sum, map.get(sum) + 1);
            }
            if (i == 0) map.put(sum + list.get(list.size() - 1), 1);
        }
    }

    static int solution() {
        makePiece(aMap, a);
        makePiece(bMap, b);
        aMap.put(0,1);
        bMap.put(0,1);
        int ans = 0;
        for (Integer piece : aMap.keySet()) {
            if(bMap.containsKey(target-piece))
                ans+=aMap.get(piece)*bMap.get(target-piece);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            a.add(Integer.valueOf(bf.readLine()));
        }
        for (int i = 0; i < n; i++) {
            b.add(Integer.valueOf(bf.readLine()));
        }
        System.out.println(solution());

    }

}
