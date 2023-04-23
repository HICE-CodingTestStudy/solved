package soobin;

import java.io.*;
import java.util.Stack;

public class IronStick {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();
        String str = br.readLine();
        int sum = 0;

        stack.push(str.charAt(0));
        boolean prev = str.charAt(0) == '(' ? true : false; // 바로 이전 괄호가 여는 괄호였으면 true, 아니면 false
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
                prev = true;
                continue;
            }

            stack.pop();
            sum = prev ? sum + stack.size() : sum + 1;
            prev = false;
        }

        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();
    }
}
