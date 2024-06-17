import java.io.*;

public class INFP {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        long total = 0;
        int max = -1;
        for (int i = 0; i < N; i++) {
            int cloth = Integer.parseInt(input[i]);
            max = Math.max(cloth, max);
            total += cloth;
        }

        boolean isHappy = total == 1 || max <= (total / 2);
        bw.write(isHappy ? "Happy" : "Unhappy");
        bw.flush();
    }
}
