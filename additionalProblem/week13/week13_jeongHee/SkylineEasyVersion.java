package additional;

import java.util.Scanner;
import java.util.Stack;

public class SkylineEasyVersion {
    //https://www.acmicpc.net/problem/1863
    //스카이라인 쉬운거
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            sc.nextInt();
            int y = sc.nextInt();
            if (stack.isEmpty() || stack.peek() < y) {
                stack.push(y);
                continue;
            }
            if (stack.peek() > y) {
                while (!stack.isEmpty() && stack.peek() > y) {
                    stack.pop();
                    ans++;
                }
                if (stack.isEmpty() || stack.peek() != y)
                    stack.push(y);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != 0)
                ans++;
        }
        System.out.println(ans);
    }

}
