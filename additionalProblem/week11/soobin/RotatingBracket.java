package week11.soobin;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RotatingBracket {
    private boolean isPair(char open, char close) {
        if (open == '(') return close == ')';
        if (open == '{') return close == '}';
        if (open == '[') return close == ']';
        return false;
    }

    private boolean isCorrect(StringBuilder sb) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < sb.length(); i++)
            stack.push(sb.charAt(i));

        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (c == ')' || c == '}' || c == ']') {
                temp.push(c);
                continue;
            }

            if (temp.isEmpty() || !isPair(c, temp.pop())) return false;
        }

        return temp.isEmpty();
    }

    public int solution(String s) {
        Queue<Character> brackets = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        for (char c : s.toCharArray())
            brackets.add(c);

        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isCorrect(sb))
                answer++;
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }

        return answer;
    }

    public static void main(String[] args) {
        RotatingBracket r = new RotatingBracket();
        System.out.println(r.solution("[](){}"));
        System.out.println(r.solution("}]()[{"));
        System.out.println(r.solution("[)(]"));
        System.out.println(r.solution("}}}"));
    }
}
