package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TwistedPowerLine {
    //https://www.acmicpc.net/problem/1365
    //꼬인 전깃줄
    static int N;
    static List<Integer> order = new ArrayList<>();
    static List<Integer> dp = new ArrayList<>();

    static int binarySearch(int target) {
        int left = 0;
        int right = dp.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target <= dp.get(mid)) right = mid;
            else left = mid + 1;
        }
        return right;
    }

    static int solution() {
        dp.add(0);
        for (int i = 0; i < order.size(); i++) {
            if (dp.get(dp.size() - 1) < order.get(i)) {
                dp.add(order.get(i));
                continue;
            }
            dp.set(binarySearch(order.get(i)), order.get(i));
        }
        return dp.size() - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            order.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(order.size() - solution());
    }
}
