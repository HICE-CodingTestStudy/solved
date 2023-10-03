package additional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class StackDice {
    //https://www.acmicpc.net/problem/2116
    //주사위 쌓기
    static int ans = 0;
    static ArrayList<ArrayList<Integer>> dices = new ArrayList<>();
    static ArrayList<HashMap<Integer, Integer>> opposite = new ArrayList<>();

    public static int findMax(Collection<Integer> arr) {
        int max = 0;
        for (Integer integer : arr) {
            max = Math.max(integer, max);
        }
        return max;
    }

    public static void solution(int now) {
        int ret = 0;
        for (int i = 0; i < dices.size(); i++) {
            int down = now;
            int up = opposite.get(i).get(down);
            dices.get(i).removeIf(n -> n == down);
            dices.get(i).removeIf(n -> n == up);
            ret += findMax(dices.get(i));
            dices.get(i).add(up);
            dices.get(i).add(down);
            now = up;
        }
        ans = Math.max(ans, ret);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            dices.add(new ArrayList<>());
            opposite.add(new HashMap<>());
            for (int j = 0; j < 6; j++) {
                dices.get(i).add(sc.nextInt());
            }
            opposite.get(i).put(dices.get(i).get(0), dices.get(i).get(5));
            opposite.get(i).put(dices.get(i).get(5), dices.get(i).get(0));
            opposite.get(i).put(dices.get(i).get(1), dices.get(i).get(3));
            opposite.get(i).put(dices.get(i).get(3), dices.get(i).get(1));
            opposite.get(i).put(dices.get(i).get(2), dices.get(i).get(4));
            opposite.get(i).put(dices.get(i).get(4), dices.get(i).get(2));
        }
        for (int i = 0; i < 6; i++) {
            solution(i+1);
        }
        System.out.println(ans);
    }

}
