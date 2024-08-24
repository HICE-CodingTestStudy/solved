package fin.HICE.week56.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15729_roomescape {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String str = br.readLine();
        boolean[] arr = new boolean[n];

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if ((str.charAt(i*2)=='1') != arr[i]) {
                answer++;
                for (int j = i; j < Math.min(i+3, n); j++) {
                    arr[j] = !arr[j];
                }
            }
        }

        System.out.println(answer);


    }
}
