package fin.HICE.week52.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28018_time_overlap {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] seats = new int[1_000_002];

        // 각 사용자가 자리를 차지하기 시작하는 시간(s), 더 이상 차지하지 않는 시간(e+1) 표시
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            seats[Integer.parseInt(st.nextToken())]++;
            seats[Integer.parseInt(st.nextToken())+1]--;
        }

        // 모든 시간 다 돌며 누적합 구함
        for (int i = 1; i < 1_000_001; i++) {
            seats[i] += seats[i-1];
        }

        int q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            sb.append(seats[Integer.parseInt(st.nextToken())]).append("\n");
        }
        System.out.println(sb);

    }
}