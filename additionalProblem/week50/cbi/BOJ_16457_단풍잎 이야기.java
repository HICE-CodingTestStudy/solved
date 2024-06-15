import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		int[] skills = new int[m];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < k; j++) {
				int type = Integer.parseInt(st.nextToken());
				skills[i] += (1 << (type - 1));
			}
		}
		int ans = 0;
		for(int key = 0; key < (1 << (2 * n)); key++) {
			int temp = key, check = 0, cnt = 0;
			while(temp > 0) {
				if(temp % 2 != 0) check++;
				temp /= 2;
			}
			if(check > n) continue;
			for(int i = 0; i < m; i++) {
				if((key & skills[i]) == skills[i]) cnt++;
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
}