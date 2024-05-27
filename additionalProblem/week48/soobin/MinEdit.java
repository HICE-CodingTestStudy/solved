import java.io.*;

public class MinEdit {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String A = br.readLine(), B = br.readLine();
        int[][] memo = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++)
            memo[i][0] = memo[i - 1][0] + 1;
        for (int i = 1; i <= B.length(); i++)
            memo[0][i] = memo[0][i - 1] + 1;

        for (int i = 1; i <= A.length(); i++)
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                    continue;
                }

                int replace = memo[i - 1][j - 1] + 1;
                int delete = memo[i - 1][j] + 1;
                int add = memo[i][j - 1] + 1;
                memo[i][j] = Math.min(replace, Math.min(delete, add));
            }

        bw.write(String.valueOf(memo[A.length()][B.length()]));
        bw.flush();
    }
}
