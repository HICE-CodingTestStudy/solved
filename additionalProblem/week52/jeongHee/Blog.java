import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/21921
    //블로그
    static int N, X;
    static int[] info;

    static int[] solution() {
        int count = 0;
        int sum = 0;
        int ans1 = 0, ans2 = 0;
        for (int i = 0; i < N; i++) {
            if (count < X) {
                sum += info[i];
                count++;
            }
            else {
                sum -= info[i - X];
                sum += info[i];
            }
            if (sum == ans1) {
                ans2++;
            }
            else if (sum > ans1) {
                ans1 = sum;
                ans2 = 1;
            }
        }
        if(ans1==0) return null;
        return new int[]{ans1, ans2};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        info = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            info[i] = Integer.parseInt(st.nextToken());
        }
        int[] ans = solution();
        if(ans==null) System.out.printf("SAD");
        else {
            System.out.println(ans[0]);
            System.out.println(ans[1]);
        }
    }
}