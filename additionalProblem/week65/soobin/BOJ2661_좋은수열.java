import java.io.BufferedReader;
import java.io.InputStreamReader;

class GoodNum {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String answer;
    private static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        solution("");
        System.out.println(answer);
    }

    private static void solution(String str) {
        if (answer != null) return;

        if (str.length() == N) {
            answer = str;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (isValid(str+i)) solution(str+i);
        }
    }

    private static boolean isValid(String target) {
        int len = target.length();

        for (int i = 1; i <= len/2; i++) {
            String left = target.substring(len - i*2, len - i);
            String right = target.substring(len - i, len);
            if (left.equals(right)) return false;
        }

        return true;
    }
}