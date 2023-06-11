import java.io.*;

public class Tilling {
    private static int[] memo = new int[1000];

    private static int tile(int n) {
        if (n == 0) return memo[0] = 1;
        if (n == 1) return memo[1] = 2;

        if (memo[n] > 0) return memo[n];

        memo[n] = (tile(n-2) + tile(n-1)) % 10007;
        return memo[n];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(tile(N-1)));
        bw.flush();
    }
}
