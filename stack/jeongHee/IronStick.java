package stack;

import java.util.Scanner;
import java.util.Stack;

public class IronStick {
    public static class BracketInfo{
        public char bracket;
        public int index;
        public BracketInfo(char bracket, int i) {
            this.bracket =bracket;
            this.index = i;
        }
    }
    public static void main(String[] args) {
        //쇠막대기
        //https://www.acmicpc.net/problem/10799
        Scanner sc = new Scanner(System.in);
        String stick = sc.next();
        Stack<BracketInfo> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < stick.length(); i++) {
            if(stick.charAt(i)=='('){
                if(!stack.isEmpty()&&stack.peek().bracket=='('&&stack.peek().index+1==i){
                    ans++;
                }
                stack.push(new BracketInfo(stick.charAt(i),i));
                continue;
            }
            if(stack.peek().bracket=='('){
                if(stack.peek().index+1!=i){
                    stack.pop();
                    continue;
                }
                stack.pop();
                ans+=stack.size();
            }
        }
        System.out.println(ans);

    }

}
