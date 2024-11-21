package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Stone {
    // https://www.acmicpc.net/problem/12886
    // 돌 그룹
    static int[][] move = {{0, 1}, {0, 2}, {1, 2}};
    static Set<String> visited;

    static boolean solution(int[] info) {
        if(info[0]==info[1] && info[1]==info[2]) return true;
        for (int i = 0; i < 3; i++) {
            int l = move[i][0];
            int r = move[i][1];
            if (info[l] == info[r]) continue;
            int[] tmp = info.clone();
            tmp[r] -= info[l];
            tmp[l] += info[l];
            Arrays.sort(tmp);
            String next = tmp[0] + " " + tmp[1] + " " + tmp[2];
            if(visited.contains(next)) continue;
            visited.add(next);
            if(tmp[0]==tmp[1] && tmp[1]==tmp[2]) return true;
            if(solution(tmp)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] first = new int[]{a, b, c};
        visited = new HashSet<>();
        Arrays.sort(first);
        visited.add(first[0] + " " + first[1] + " " + first[2]);
        boolean ans = solution(first);
        System.out.println(ans ? 1 : 0);
    }
}
