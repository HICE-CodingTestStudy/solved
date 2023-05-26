package soobin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GymSuit {
    private static boolean isReserve(List<Integer> list, int student) {
        for (int r : list) {
            if (r == student) {
                list.remove(list.indexOf(r));
                return true;
            }
        }
        return false;
    }
    public static int solution(int n, int[] lost, int[] reserve) {
        List<Integer> reserveList = new ArrayList<>();
        List<Integer> lostList = new ArrayList<>();
        for (int l : lost) lostList.add(l);
        for (int r : reserve) {
            if (!lostList.contains(r)) reserveList.add(r);
            else lostList.remove(lostList.indexOf(r));
        }
        Collections.sort(reserveList);
        Collections.sort(lostList);
        int answer = n - lostList.size();

        for (int l : lostList) {
            int prev = l - 1;
            int next = l + 1;

            if (isReserve(reserveList, prev)) { answer++; continue;}
            if (isReserve(reserveList, next)) answer++;
        }

        return answer;
    }

    public static void main(String[] agrs) {
        int n = 5;
        int[] lost = {3,1};
        int[] reserve = {2,4};

        System.out.println(solution(n, lost, reserve));
    }
}
