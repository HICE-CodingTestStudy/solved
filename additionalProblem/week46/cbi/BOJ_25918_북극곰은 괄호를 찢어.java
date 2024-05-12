import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] c = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		for(int i = 0; i < N; i++) {
			answer = Math.max(answer, stack.size());
			if(stack.isEmpty() || stack.peek() == c[i]) stack.add(c[i]);
			else stack.pop();
		}
		System.out.println(stack.isEmpty() ? answer : -1);
	}
}