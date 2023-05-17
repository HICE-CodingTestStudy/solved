package soobin;

import java.util.HashSet;
import java.util.Set;

public class FindPrimeNumber {

    private static Set<Integer> primes = new HashSet<>();
    private static char[] numChars;

    private static void generate(String generated, boolean[] used, int length) {
        if (generated.length() == length) {
            int number = Integer.parseInt(generated);
            if (isPrime(number)) primes.add(number);
            return;
        }

        for (int i = 0; i < numChars.length; i++) {
            if (!used[i]) {
                generated += String.valueOf(numChars[i]);
                used[i] = true;
                generate(generated, used, length);
                used[i] = false;
                generated = "";
            }
        }
    }

    private static boolean isPrime(int number) {
        System.out.println(number);
        if (number == 1 || number == 0) return false;

        for (int i = 2; i*i <= number; i++)
            if (number % i == 0) return false;
        return true;
    }

    public static int solution(String numbers) {
        numChars = numbers.toCharArray();
        boolean[] isUsed = new boolean[numChars.length];

        for (int numLength = 1; numLength <= numChars.length; numLength++)
            generate("", isUsed, numLength);

        return primes.size();
    }

    public static void main(String[] args) {
        String numbers = "223";
        System.out.println(solution(numbers));
        System.out.println(primes);
    }

}
