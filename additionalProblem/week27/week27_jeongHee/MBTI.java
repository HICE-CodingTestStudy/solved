package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MBTI {
    //https://www.acmicpc.net/problem/20529
    //가장 가까운 세 사람의 심리적 거리
    static List<String> mbti = new ArrayList<>();
    static Map<String, Integer> hm;
    static List<Distance> cases = new ArrayList<>();

    static class Distance implements Comparable<Distance> {
        List<String> mbti;
        int distance;

        private int getDistance(String a, String b) {
            int ret = 0;
            for (int i = 0; i < 4; i++) {
                if (a.charAt(i) != b.charAt(i)) ret++;
            }
            return ret;
        }

        public Distance(List<String> mbti) {
            this.mbti = mbti;
            this.distance += getDistance(mbti.get(0), mbti.get(1))
                    + getDistance(mbti.get(1), mbti.get(2))
                    + getDistance(mbti.get(0), mbti.get(2));
        }

        @Override
        public int compareTo(Distance o) {
            return distance - o.distance;
        }
    }

    static void addMbti(String a, String b) {
        List<String> tmpMbti = new ArrayList<>();
        for (String s : mbti) {
            tmpMbti.add(s + a);
            tmpMbti.add(s + b);
        }
        mbti.clear();
        mbti.addAll(tmpMbti);
    }

    static void makeMbtiList() {
        mbti.add("E");
        mbti.add("I");
        addMbti("N", "S");
        addMbti("F", "T");
        addMbti("P", "J");
    }

    static void makeCase(List<String> visited, int count) {
        if (count == 3) {
            cases.add(new Distance(new ArrayList<>(visited)));
            return;
        }
        for (int i = 0; i < mbti.size(); i++) {
            visited.add(mbti.get(i));
            makeCase(visited, count + 1);
            visited.remove(visited.size() - 1);
        }
    }

    static int solution() {
        for (int i = 0; i < cases.size(); i++) {
            boolean isValid = true;
            Map<String, Integer> tmpMap = new HashMap<>(hm);
            for (int j = 0; j < cases.get(i).mbti.size(); j++) {
                String person = cases.get(i).mbti.get(j);
                if (!tmpMap.containsKey(person) || tmpMap.get(person) == 0) {
                    isValid = false;
                    break;
                }
                tmpMap.put(person, tmpMap.get(person) - 1);
            }
            if (isValid) return cases.get(i).distance;
        }
        return 12;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        makeMbtiList();
        makeCase(new ArrayList<>(), 0);
        Collections.sort(cases);
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int cnt = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            hm = new HashMap<>();
            while (cnt-- > 0) {
                String next = st.nextToken();
                hm.putIfAbsent(next, 0);
                hm.put(next, hm.get(next) + 1);
            }
            System.out.println(solution());
        }
    }
}
