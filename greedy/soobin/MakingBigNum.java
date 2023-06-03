package soobin;

public class MakingBigNum {
    public static String solution(String number, int k) {
        String answer = "";
        char[] digits = number.toCharArray();

        int start = 0;
        while (answer.length() < number.length() - k) {
            int tempMax = Integer.parseInt(String.valueOf(digits[start]));

            for (int i = start + 1; i <= k + answer.length(); i++) {
                int cur = Integer.parseInt(String.valueOf(digits[i]));
                if (cur > tempMax) {
                    tempMax = cur;
                    start = i;

                    if (tempMax == 9) break;
                }
            }

            start++;
            answer += String.valueOf(tempMax);
        }

        return answer;
    }

    public static void main(String[] args) {
        String number = "4177252841";
        int k = 4;
        System.out.println(solution(number, k));
    }
}
