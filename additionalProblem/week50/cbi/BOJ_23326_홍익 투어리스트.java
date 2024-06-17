import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		TreeSet<Integer> set = new TreeSet<>();

		for(int i = 0; i < N; i++) {
			if(st.nextToken().equals("1")) {
				set.add(i);
			}
		}

		int loc = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			if(query == 1) {
				int j = Integer.parseInt(st.nextToken()) - 1;
				if(set.contains(j)) set.remove(j);
				else set.add(j);
			}
			else if(query == 2) {
				loc = (loc + Integer.parseInt(st.nextToken())) % N;
			}
			else {
				if(set.isEmpty()) sb.append(-1);
				else if(set.contains(loc)) sb.append(0);
				else {
					Integer find = set.higher(loc);
					if(find == null) sb.append(N + set.first() - loc);
					else sb.append(find - loc);
				}
				sb.append('\n');
			}
		}

		System.out.println(sb);
	}
}