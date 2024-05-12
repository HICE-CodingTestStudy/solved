import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    //https://www.acmicpc.net/problem/25918
    //북극곰은 괄호를 찢어
    static int N;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        s = bf.readLine();
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, stack.size());
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.add(c);
                continue;
            }
            if (stack.peek() == c) {
                stack.add(c);
                continue;
            }
            stack.pop();
        }
        if(!stack.isEmpty()){
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }
}