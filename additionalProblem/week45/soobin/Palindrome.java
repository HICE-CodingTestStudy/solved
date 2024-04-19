public class Palindrome {
    public int solution(String s) {
        int answer = -1;

        for (int i = 0; i < s.length(); i++) {
            int odd = substring(s, i - 1, i + 1) + 1;
            int even = substring(s, i, i + 1);
            int max = Math.max(odd, even);
            answer = Math.max(answer, max);
        }

        return answer;
    }

    private int substring(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count += 2;
            } else break;
        }

        return count;
    }
}
