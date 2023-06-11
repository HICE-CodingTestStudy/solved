import java.io.*;

public class Sum123 {
    private static int[] memo = new int[10];

    private static int sum(int n) {
        if (n == 0) return memo[0] = 1;
        if (n == 1) return memo[1] = 2;
        if (n == 2) return memo[2] = 4;

        if(memo[n] > 0) return memo[n];

        memo[n] = sum(n-1) + sum(n-2) + sum(n-3);
        return memo[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(sum(N-1)));
            bw.newLine();
        }
        bw.flush();
    }
}
