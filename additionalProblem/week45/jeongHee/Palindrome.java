public class Palindrome {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12904
    //가장 긴 팰린드롬
    static int getAns(String s, int left, int right, int count) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                count += 2;
                left--;
                right++;
                continue;
            }
            break;
        }
        return count;
    }

    public int solution(String s) {
        int ans = 1;
        for (int i = 0; i < s.length(); i++) {
            ans = Math.max(ans, getAns(s, i - 1, i + 1, 1));
            ans = Math.max(ans, getAns(s, i, i + 1, 0));
        }
        return ans;
    }
}
