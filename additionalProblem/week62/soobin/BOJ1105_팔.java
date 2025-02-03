import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Eight {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String L = st.nextToken();
        String R = st.nextToken();

        System.out.println(solution(L, R));
    }

    private static int solution(String L, String R) {
        if (L.length() < R.length()) return 0;

        int atLeast = 0;
        for (int i = 0; i < L.length(); i++) {
            if (L.charAt(i) != R.charAt(i)) break;
            if (L.charAt(i) == '8') atLeast++;
        }

        return atLeast;
    }
}