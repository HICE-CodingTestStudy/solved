package week10.soobin;

import java.util.*;

public class ToothbrushSales {
    private Map<String, String> parent;
    private Map<String, Integer> totalProfits;

    private void setParent(String[] enrolls, String[] referrals) {
        for (int i = 0; i < enrolls.length; i++)
            parent.put(enrolls[i], referrals[i]);
    }

    private void calculateProfits(String seller, int amount) {
        if (amount == 0) return;

        int tenOfProfit = (int) (amount * 0.1);
        totalProfits.put(seller, totalProfits.getOrDefault(seller, 0) + amount - tenOfProfit);
        calculateProfits(parent.get(seller), tenOfProfit);
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        parent = new HashMap<>();
        totalProfits = new HashMap<>();

        setParent(enroll, referral);

        for (int i = 0; i < seller.length; i++) {
            amount[i] *= 100;
            calculateProfits(seller[i], amount[i]);
        }

        for (int i = 0; i < enroll.length; i++)
            answer[i] = totalProfits.getOrDefault(enroll[i], 0);

        return answer;
    }

    private static void printResult(int[] result) {
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            System.out.print(String.format("%d, ", result[i]));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        ToothbrushSales t = new ToothbrushSales();
        printResult(t.solution(
                new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"young", "john", "tod", "emily", "mary"},
                new int[] {12, 4, 2, 5, 10}
        ));
        printResult(t.solution(
                new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[] {"sam", "emily", "jaimie", "edward"},
                new int[] {2, 3, 5, 4}
        ));
    }
}
