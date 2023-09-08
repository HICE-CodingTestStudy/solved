package eighth.soobin;

import java.io.*;
import java.util.*;

public class YachtDice {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INITIAL_DICE_NUM = 3;

    private static int[] initialDice = new int[INITIAL_DICE_NUM];
    private static Map<Integer, Integer> diceMap = new HashMap<>();

    private static void putDiceToMap() {
        for (int i = 0; i < INITIAL_DICE_NUM; i++)
            diceMap.put(initialDice[i], diceMap.getOrDefault(initialDice[i], 0) + 1);
    }

    private static int numOfIthDice(int i) {
        return (diceMap.getOrDefault(i, 0) + 2) * i;
    }

    private static int fourOfAKind() {
        for (int key : diceMap.keySet())
            if (diceMap.get(key) >= 2) return key * 4;

        return 0;
    }

    private static int fullHouse() {
        if (diceMap.keySet().size() > 2) return 0;

        // 현재 3개 다 같은 주사위
        if (diceMap.keySet().size() == 1)
            return initialDice[0] == 6 ? 28 : initialDice[0] * 3 + 12; // 18 + 10 : n * 3 + 12

        // 현재 주사위 두 종류
        int first = initialDice[0];
        int second = initialDice[1] != first ? initialDice[1] : initialDice[2];

        return first > second ? first * 3 + second * 2 : second * 3 + first * 2;
    }

    private static int littleStraight() {
        return diceMap.keySet().size() != 3 || diceMap.keySet().contains(6) ? 0 : 30;
    }

    private static int bigStraight() {
        return diceMap.keySet().size() != 3 || diceMap.keySet().contains(1) ? 0 : 30;
    }

    private static int yacht() {
        return diceMap.keySet().size() == 1 ? 50 : 0;
    }

    private static int choice() {
        int total = 0;
        for (int i = 0; i < INITIAL_DICE_NUM; i++)
            total += initialDice[i];

        return total + 12;
    }

    public static void main(String[] args) throws IOException {
        String rules = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < INITIAL_DICE_NUM; i++)
            initialDice[i] = Integer.parseInt(st.nextToken());
        putDiceToMap();

        int max = 0;
        // Ones ~ Sixes
        for (int i = 0; i < 6; i++)
            if (rules.charAt(i) == 'Y')
                max = Math.max(max, numOfIthDice(i + 1));
        // Four of a Kind
        if (rules.charAt(6) == 'Y') max = Math.max(max, fourOfAKind());
        // Full House
        if (rules.charAt(7) == 'Y') max = Math.max(max, fullHouse());
        // Little Straight
        if (rules.charAt(8) == 'Y') max = Math.max(max, littleStraight());
        // Big Straight
        if (rules.charAt(9) == 'Y') max = Math.max(max, bigStraight());
        // Yacht
        if (rules.charAt(10) == 'Y') max = Math.max(max, yacht());
        // Choice
        if (rules.charAt(11) == 'Y') max = Math.max(max, choice());

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
