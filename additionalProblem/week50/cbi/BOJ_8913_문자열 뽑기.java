import java.io.*;
import java.util.*;

public class Main {
	static boolean ans;
	static void isCheck(StringBuilder c) {
		if(ans) return;
		if(c.length() == 1) {
			ans = !c.toString().equals("F");
			return;
		}
		for(int i = 0; i < c.length(); i++) {
			if(c.charAt(i) == 'F') continue;
			StringBuilder temp = new StringBuilder(c.toString());
			if(i == 0 || i == c.length() - 1) {
				isCheck(temp.replace(i, i + 1, ""));
			}
			else {
				isCheck(temp.replace(i - 1, i + 2, "T"));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(--T >= 0) {
			char[] S = br.readLine().toCharArray();
			ArrayDeque<int[]> freq = new ArrayDeque<>();
			ans = false;
			for(int i = 0; i < S.length; i++) {
				int val = S[i] - 'a';
				if(freq.isEmpty() || freq.peekLast()[0] != val) {
					freq.add(new int[] {val, 1});
				}
				else {
					freq.peekLast()[1]++;
				}
			}
			StringBuilder C = new StringBuilder();
			for(int[] now : freq) {
				if(now[1] > 1) C.append('T');
				else C.append('F');
			}
			isCheck(C);
			sb.append(ans ? 1 : 0).append('\n');
		}
		System.out.println(sb);
	}
}