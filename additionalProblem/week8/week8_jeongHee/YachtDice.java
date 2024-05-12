package additional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class YachtDice {
    //https://www.acmicpc.net/problem/27162
    //요트 다이스
    static String valid;
    static int[] dice;

    public static int onesToSixes(int n) {
        int ans = 0;
        for (int num : dice) {
            if (num == n) ans += n;
        }
        return ans + 2 * n;
    }

    public static int fourOfAKind() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : dice) {
            hm.merge(num, 1, Integer::sum);
        }
        for (Integer integer : hm.keySet()) {
            if (hm.get(integer) >= 2) return integer * 4;
        }
        return 0;
    }

    public static int fullHouse() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : dice) {
            hm.merge(num, 1, Integer::sum);
        }
        if (hm.keySet().size() >= 3) return 0;
        else if (hm.keySet().size() == 2) {
            int ans = 0;
            int one = 0;
            int two = 0;
            for (Integer integer : hm.keySet()) {
                if (hm.get(integer) == 1) one = integer;
                else two = integer;
            }
            return Math.max(one, two) * 3 + Math.min(one, two) * 2;
        } else {
            int ans = 0;
            for (Integer integer : hm.keySet()) {
                ans += integer * 3;
            }
            //이부분 고려 필수
            if(ans==18) return ans+5*2;
            else return ans + 6 * 2;
        }
    }

    public static int littleStraight() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : dice) {
            hm.merge(num, 1, Integer::sum);
        }
        for (Integer integer : hm.keySet()) {
            if (integer == 6) return 0;
        }
        if (hm.keySet().size() <= 2) return 0;
        return 30;
    }

    public static int bigStraight() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : dice) {
            hm.merge(num, 1, Integer::sum);
        }
        if (hm.keySet().size() <= 2) return 0;
        for (Integer integer : hm.keySet()) {
            if (integer == 1) return 0;
        }
        return 30;
    }

    public static int yacht() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num : dice) {
            hm.merge(num, 1, Integer::sum);
        }
        if (hm.keySet().size() > 1) return 0;
        else return 50;
    }

    public static int choice() {
        int ans = 0;
        for (int num : dice) {
            ans += num;
        }
        return ans + 6 * 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        valid = sc.next();
        dice = new int[3];
        for (int i = 0; i < 3; i++) {
            dice[i] = sc.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < 6; i++) {
            if (valid.charAt(i) == 'Y')
                ans = Math.max(ans, onesToSixes(i + 1));
        }
        if (valid.charAt(6) == 'Y') {
            ans = Math.max(ans, fourOfAKind());
        }
        if (valid.charAt(7) == 'Y') {
            ans = Math.max(ans, fullHouse());
        }
        if (valid.charAt(8) == 'Y') {
            ans = Math.max(ans, littleStraight());
        }
        if (valid.charAt(9) == 'Y') {
            ans = Math.max(ans, bigStraight());
        }
        if (valid.charAt(10) == 'Y') {
            ans = Math.max(ans, yacht());
        }
        if (valid.charAt(11) == 'Y') {
            ans = Math.max(ans, choice());
        }
        System.out.println(ans);
    }

}
