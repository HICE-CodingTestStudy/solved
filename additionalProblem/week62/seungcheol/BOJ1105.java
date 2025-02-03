import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String L = st.nextToken();
        String R = st.nextToken();

        //두 수의 10^N 승이 다르면 (ex. 99 100)10^1, 10^2이런식으로 무조건 8은 0개가 가능
        if (L.length() != R.length()) {
            System.out.println(0);
        } else { //자리수가 같으면
            System.out.println(find(L, R));
        }


    }

    private static int find(String L, String R) {
        int idx = -1;
        final int SIZE = L.length();
        //달라지는 부분의 자리수 확인
        for (int i = 0; i < SIZE; i++) {
            if(L.charAt(i) == R.charAt(i)) {
                idx = i;
                continue;
            }
            break;
        }
        int cnt = 0;
        //L, R이 같은 앞부분에서 8개수 찾기
        for (int i = 0; i <= idx; i++) {
            if(L.charAt(i) == '8') cnt++;
        }
        return cnt;
    }

}