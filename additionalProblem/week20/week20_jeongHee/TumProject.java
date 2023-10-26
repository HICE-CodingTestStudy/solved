package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TumProject {
    //https://www.acmicpc.net/problem/9466
    //텀프로젝트
    static int N;
    static Set<Integer> unMatched = new HashSet<>();
    static Set<Integer> matched = new HashSet<>();
    static Map<Integer, Integer> want = new HashMap<>();

    static void init(){
        unMatched.clear();
        matched.clear();
        want.clear();
    }
    static void solution(int student) {
        if (unMatched.contains(student) || matched.contains(student)) return;
        ArrayList<Integer> visited = new ArrayList<>(); //이제까지 등장한 학생
        Map<Integer, Integer> visitedAt = new HashMap<>(); //해당 학생이 몇초(몇번째)에 등장했는지
        int index = 0;
        visited.add(student);
        visitedAt.put(student, index++);
        int next = want.get(student);
        while (true) {
            //다음사람이 이미 매칭/매칭 실패 이력이 있음
            if(unMatched.contains(next)||matched.contains(next)){
                for (int i = 0; i < index; i++) {
                    unMatched.add(visited.get(i));
                }
                return;
            }
            //매칭됨(사이클이 있음)
            if(visitedAt.containsKey(next)){
                //해당 학생이 처음으로 등장한 때보다 이전에 등장한 학생은 매칭 불가
                for (int i = 0; i < visitedAt.get(next); i++) {
                    unMatched.add(visited.get(i));
                }
                //해당학생이 처음으로 등장한 때부터 재등장할때까지 등장한 학생은 매칭
                for (int i = visitedAt.get(next); i < index ; i++) {
                    matched.add(visited.get(i));
                }
                return;
            }
            //해당사항 없다면 다음 학생을 탐색함
            visited.add(next);
            visitedAt.put(next,index++);
            next = want.get(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            init();
            N = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                want.put(i + 1, Integer.valueOf(st.nextToken()));
            }
            for (int i = 0; i < N; i++) {
                solution(i + 1);
            }
            sb.append(unMatched.size()).append("\n");
        }
        System.out.println(sb);
    }
}
