import java.util.*;
import java.io.*;
public class Main {
	static String solution(char[] arr, int order) {
		Arrays.sort(arr);
		int divide = 1;
		int count = arr.length;
		while (--count > 0) {
			divide *= count;
		}
		StringBuilder ans = new StringBuilder();
		int dCount = arr.length - 1;
		while (true) {
			int index = order / divide;
			if(index>=arr.length) {
				return "No permutation";
			}
			ans.append(arr[index]);
			order -= (order / divide) * divide;

			if(dCount==0) break;
			divide /= dCount;
			dCount--;

			char[] next = new char[arr.length - 1];
			int add = 0;
			for (int i = 0; i < arr.length; i++) {
				if(i==index) {
					add = -1;
					continue;
				}
				next[add + i] = arr[i];
			}
			arr = next;
		}
		return ans.toString();
	}
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String query;
		StringBuilder sb = new StringBuilder();
		while((query=bf.readLine())!=null && !query.isEmpty()){
			StringTokenizer st = new StringTokenizer(query);
			String origin = st.nextToken();
			char[] arr = origin.toCharArray();
			int order = Integer.parseInt(st.nextToken());
			String ans = solution(arr, order-1);
			sb.append(origin).append(" ").append(order).append(" = ").append(ans).append("\n");
        }
		System.out.println(sb);
    }
}
