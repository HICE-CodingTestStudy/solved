package fin.HICE.week51.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25186_INFP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean answer = true;

        long[] arr = new long[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        long sum = arr[0], max = arr[0];
        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }


        if((sum)/2 >= max || sum==1){
            System.out.println("Happy");
        } else{
            System.out.println("Unhappy");
        }

    }
}
