package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MakingTeam {
    //https://www.acmicpc.net/problem/11578
    //팀원 모집
    static int N, M;
    static int ans = 10;
    static List<List<Integer>> can = new ArrayList<>();
    static Map<Integer, Integer> solved = new HashMap();
    static Set<Integer> teamMates = new HashSet<>();

    static void solution() {
        if (solved.keySet().size() != N) return;
        ans = Math.min(teamMates.size(), ans);
        Set<Integer> tmpTM = new HashSet<>(teamMates);
        for (Integer teamMate : tmpTM) {
            for (int i : can.get(teamMate)) {
                solved.put(i, solved.get(i) - 1);
                if (solved.get(i) == 0) solved.remove(i);
            }
            teamMates.remove(teamMate);
            solution();
            teamMates.add(teamMate);
            for (int i : can.get(teamMate)) {
                solved.putIfAbsent(i, 0);
                solved.put(i, solved.get(i) + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            can.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            teamMates.add(i);
            for (int j = 0; j < cnt; j++) {
                int problem = Integer.parseInt(st.nextToken());
                can.get(i).add(problem);
                solved.putIfAbsent(problem, 0);
                solved.put(problem, solved.get(problem) + 1);
            }
        }
        if (solved.keySet().size() != N) {
            System.out.println(-1);
            return;
        }
        solution();
        System.out.println(ans);

    }
}
