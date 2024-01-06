package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SelectNumber {
    //https://www.acmicpc.net/problem/2230
    //수 고르기
    static int N, M;
    static List<Integer> nums = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    static void solution() {
        Collections.sort(nums);
        int start = 0;
        int end = 0;
        while (start < nums.size() && end < nums.size()) {
            int diff = nums.get(end) - nums.get(start);
            if (diff >= M) {
                if (ans > diff) ans = diff;
                start++;
            } else end++;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(bf.readLine()));
        }
        solution();
        System.out.println(ans);
    }
}
