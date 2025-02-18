import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Jujeol {
    //https://www.acmicpc.net/problem/1897
    //토달기
    static int D;
    static String start;
    static List<String> strings = new ArrayList<>();
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] dp;
    static int ansCount, ans;

//    static void init() {
//        for (int i = 0; i < D ; i++) {
//            graph.add(new ArrayList<>());
//        }
//        for (int i = 0; i < D ; i++) {
//            StringBuilder regex = new StringBuilder();
//            regex.append(".*");
//            for (int j = 0; j < strings.get(i).length(); j++) {
//                regex.append(strings.get(i).charAt(j));
//                regex.append(".*");
//            }
//            for (int j = 0; j < D ; j++) {
//                if (i == j) continue;
//                if (strings.get(j).matches(regex.toString()) && strings.get(j).length() == strings.get(i).length() + 1)
//                    graph.get(i).add(j);
//            }
//        }
//    }

    static void init() {
        for (int i = 0; i < D; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                if (strings.get(i).length() + 1 != strings.get(j).length()) continue;
                int plus = 0;
                for (int k = 0; k < strings.get(i).length(); k++) {
                    if (strings.get(i).charAt(k) == strings.get(j).charAt(k+plus)) continue;
                    plus++;
                    k--;
                    if(plus>1) break;
                }
                if (plus <= 1) graph.get(i).add(j);
            }
        }
    }

    static int solution(int now) {
        if (dp[now] != -1) return dp[now];
        int max = strings.get(now).length();
        for (Integer next : graph.get(now)) {
            max = Math.max(max, solution(next));
        }
        if (max > ansCount) {
            ansCount = max;
            ans = now;
        }
        return dp[now] = max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        D = Integer.parseInt(st.nextToken());
        start = st.nextToken();
        dp = new int[D];
        int startIndex = 0;
        Arrays.fill(dp, -1);
        for (int i = 0; i < D; i++) {
            strings.add(bf.readLine());
            if (strings.get(i).equals(start)) startIndex = i;
        }
        init();
        solution(startIndex);
        System.out.println(strings.get(ans));

    }
}
