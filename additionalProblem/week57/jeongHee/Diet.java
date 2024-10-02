package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Diet {
    // https://www.acmicpc.net/problem/1484
    // 다이어트
    static int G;

    static void solution() {
        StringBuilder ans = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        // 가장 좁혔는데도 (1차이) G보다 크다면 종료
        int l = 0, r = 1;
        while (l < r) {
            int gap = list.get(r) * list.get(r) - list.get(l) * list.get(l);
            if (gap == G) {
                ans.append(list.get(r))
                   .append("\n");
                r++;
                list.add(list.size() + 1);
            } else if (gap > G) {
                l++;
            } else {
                r++;
                list.add(list.size() + 1);
            }
        }
        if(ans.toString().equals("")) System.out.println(-1);
        else System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(bf.readLine());
        solution();
    }
}
