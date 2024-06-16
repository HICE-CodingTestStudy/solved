package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class StringBbopKi {
    //https://www.acmicpc.net/problem/8913
    //문자열 뽑기
    static HashSet<String> isValid = new HashSet<>(), isInvalid = new HashSet<>();

    static boolean solution(String s) {
        if (s.length() == 1) return false;
        if (isInvalid.contains(s)) return false;
        if (isValid.contains(s)) return true;
        char c = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (c == s.charAt(i)) count++;
            else {
                if (count >= 2) {
                    if (solution(s.substring(0, i - count) + s.substring(i))) {
                        isValid.add(s);
                        return true;
                    }
                }
                count = 1;
                c = s.charAt(i);
            }
        }
        if (count == s.length()) {
            isValid.add(s);
            return true;
        }
        if (count >= 2) {
            if (solution(s.substring(0, s.length() - count))) {
                isValid.add(s);
                return true;
            }
        }
        isInvalid.add(s);
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            if (solution(bf.readLine())) System.out.println(1);
            else System.out.println(0);
        }
    }

}
