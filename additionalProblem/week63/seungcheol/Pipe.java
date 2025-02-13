import javax.naming.PartialResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] pipe = new int[N + 1][2];
        int[][] dp = new int[N + 1][X + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pipe[i] = new int[]{L, C};
        }

        for (int i = 1; i <= N; i++) {
            for (int count = 0; count <= pipe[i][1]; count++) { //해당 파이프를 count개 써서 j길이를 만들려는 것
                //파이프 개수만큼 쓰기
                int len = pipe[i][0] * count;
                if(len > X) break;
                if(count != 0) dp[i][len]++;
                for (int j = X; j >= 1; j--) {
                    if (len > j) break; //현재 구하려는 길이(j)보다 길게 쓰는건 의미없음
                    dp[i][j] += dp[i - 1][j - len];
                }
            }
        }
        System.out.println(dp[N][X]);
    }
}