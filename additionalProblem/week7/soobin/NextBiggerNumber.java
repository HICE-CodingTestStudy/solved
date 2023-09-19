package week7.soobin;

public class NextBiggerNumber {
    private final int BIGGEST_NUMBER = 1000064;

    private int countOne(int org) {
        int count = 0;

        while (org > 0) {
            if ((org & 1) == 1) count++;
            org = org >> 1;
        }

        return count;
    }

    public int solution(int n) {
        int numOfOne = countOne(n);

        for (n += 1; n <= BIGGEST_NUMBER; n++) {
            if (countOne(n) == numOfOne) return n;
        }

        return BIGGEST_NUMBER;
    }

    public static void main(String[] args) {
        NextBiggerNumber n = new NextBiggerNumber();

        System.out.println(n.solution(78));
        System.out.println(n.solution(15));
        System.out.println(n.solution(1));
        System.out.println(n.solution(44));
        System.out.println(n.solution(1000000));

        System.out.println(Integer.toBinaryString(1000000));
        System.out.println(Integer.toBinaryString(1000064));
    }
}
