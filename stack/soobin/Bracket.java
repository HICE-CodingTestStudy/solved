package soobin;

import java.io.*;
import java.util.Stack;

public class Bracket {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Stack<Object> stack = new Stack<>();
    private static int sum = 0;

    private static int getType(char c) {
        return c == ')' ? 2 : 3;
    }

    private static boolean isOpen(char c) {
        return c == '(' || c == '[';
    }

    private static boolean isPair(char prev, char next) {
        boolean isPair = false;
        if(next == ')') isPair = prev == '(' ? true : false;
        else isPair = prev == '[' ? true : false;
        return isPair;
    }

    private static void stackOperation(boolean isEmpty, int value) {
        if(isEmpty) sum += value;
        else stack.push(value);
    }

    private static void write() throws IOException {
        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        if(!isOpen(str.charAt(0))) {
            write();
            return;
        }

        stack.push(str.charAt(0));
        for(int i = 1; i < str.length(); i++) {
            char current = str.charAt(i);

            // 여는 괄호가 왔을 때 스택에 저장
            if(isOpen(current)) { stack.push(current); continue; }

            // 괄호를 열지 않고 닫는 괄호가 먼저 나옴
            if(stack.empty()) { sum = 0; break; }

            Object peek = stack.pop();
            if(peek instanceof Character && isPair((Character) peek, current)) {
                stackOperation(stack.empty(), getType(current));
            } else if (peek instanceof Integer) {
                int next = 0;
                if(stack.peek() instanceof Integer) next = (Integer) stack.pop();
                if(!isPair((Character) stack.peek(), current)) break;
                stack.pop();

                int value = next == 0 ? (Integer) peek : (Integer) peek + next;
                value *= getType(current);
                stackOperation(stack.empty(), value);
            } else { sum =0; break; }
        }

        write();
    }
}
