import java.io.*;
import java.util.StringTokenizer;

/**
 * 백준 : 21921 (블로그)
 */
public class BOJ21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(i < X) sum += num;
            arr[i] = num;
        }
        long res = sum;
        int left = 0;
        int right = X;
        int cnt = 1;
        while (right != N) {
            sum += (arr[right++] - arr[left++]);
            if(sum < res) continue;
            else if (sum > res) {
                cnt = 1;
            } else if(sum == res){
                cnt++;
            }
            res = sum;
        }
        StringBuilder sb = new StringBuilder();
        if (res == 0) {
            sb.append("SAD");
        } else {
            sb.append(res).append("\n").append(cnt);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}