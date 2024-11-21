package ing.week57.seoyoung059;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_good {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;
        int curr, left, right, mid;

        loop:
        for (int i = 0; i < n; i++) {
            curr = arr[i];
            for (int j = 0; j < n; j++) {
                if(i==j) continue;
                left = j+1;
                right = n-1;
                while(left < right) {
                    mid = (left + right)/2;
                    if(curr <= arr[j]+arr[mid]) {
                        right  = mid;
                    } else {
                        left = mid+1;
                    }
                }

                if(arr[j]+arr[right]==curr && i!=right) {
                    answer++;
                    break;
                }

                if(right+1<n && i!=right+1&&arr[j]+arr[right+1]==curr) {
                    answer++;
                    break;
                }

            }
        }

        System.out.println(answer);
    }
}
