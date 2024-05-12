package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RemoveBracket {
    //https://www.acmicpc.net/problem/2800
    //괄호 제거
    static int bracketCount = 0;
    static String formula;
    static ArrayList<HashSet<String>> dp = new ArrayList<>(); //dp(i) = i개의 괄호를 지운 문자열들의 집합
    static PriorityQueue<String> pq = new PriorityQueue<>();

    static void countBracket() {
        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') bracketCount++;
        }
    }

    //n개의 괄호를 지운 문자열 = n-1개의 괄호를 지운 문자열에서 한개의 괄호를 더 지움
    static void solution() {
        for (int i = 1; i <= bracketCount; i++) {
            for (String s : dp.get(i - 1)) {
                //총 b개의 괄호중 i-1개의 괄호를 없앤 문자열
                //b-i+1개의 괄호중 하나씩 지우기
                for (int j = 1; j <= bracketCount - i + 1; j++) {
                    StringBuilder sb = new StringBuilder();
                    int left = 0;
                    Stack<Character> stack = new Stack<>();
                    for (int k = 0; k < s.length(); k++) {
                        if(s.charAt(k)=='(') {
                            left++;
                            stack.push(s.charAt(k));
                        }
                        if(left==j) {
                            stack.push('{');
                            left++;
                            continue;
                        }
                        if(s.charAt(k)==')'){
                            if(stack.peek()=='{'){
                                stack.pop();
                                continue;
                            }
                            stack.pop();
                        }
                        sb.append(s.charAt(k));
                    }
                    dp.get(i).add(sb.toString());
                }
            }
            pq.addAll(dp.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        formula = bf.readLine();
        countBracket();
        for (int i = 0; i < bracketCount + 1; i++) {
            dp.add(new HashSet<>());
        }
        dp.get(0).add(formula);
        solution();
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty())
            sb.append(pq.poll()).append("\n");
        System.out.println(sb.toString());
    }
}
