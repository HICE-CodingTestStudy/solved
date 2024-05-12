package queue;

import javax.swing.plaf.IconUIResource;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BracketRotation {
    //https://school.programmers.co.kr/learn/courses/30/lessons/76502
    //괄호 회전하기
    public boolean check(Queue<Character> queue){
        Stack<Character> stack = new Stack<>();
        while (!queue.isEmpty()){
            if (queue.peek() == '{' || queue.peek() == '[' || queue.peek()=='(') {
                stack.add(queue.poll());
            } else {
                if(stack.isEmpty()) return false;
                Character c = queue.poll();
                if(c=='}' && stack.peek()=='{') {
                    stack.pop();
                    continue;
                }
                if(c==']' && stack.peek()=='[') {
                    stack.pop();
                    continue;
                }
                if(c==')' && stack.peek()=='(') {
                    stack.pop();
                    continue;
                }
                return false;
            }
        }
        return stack.isEmpty();
    }

    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            Queue<Character> queue = new LinkedList<>();
            for (int j = 0; j < s.length(); j++) {
                queue.add(s.charAt(j));
            }
            for (int j = 0; j < i; j++) {
                queue.add(queue.poll());
            }
            if(check(queue)) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        BracketRotation b = new BracketRotation();
        b.solution("[](){}");
    }
}
