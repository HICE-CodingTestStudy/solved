package fin.HICE.week52.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21921_Blog {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[n-x+1];
        for (int i = 0; i < x; i++) {
            sum[0]+=arr[i];
        }

        int ans = sum[0], cnt = 1;
        for (int i = 1; i < n-x+1; i++) {
            sum[i] = sum[i-1]-arr[i-1]+arr[i-1+x];
            if(sum[i] > ans) {
                ans = sum[i];
                cnt = 1;
            } else if (sum[i]==ans){
                cnt++;
            }
        }
//        System.out.println(Arrays.toString(sum));


        StringBuilder sb = new StringBuilder();
        if(ans==0) {
            sb.append("SAD");
        } else{
            sb.append(ans).append("\n").append(cnt);
        }

        System.out.println(sb);
    }
}