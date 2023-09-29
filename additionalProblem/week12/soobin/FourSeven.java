package week12.soobin;

import java.io.*;

public class FourSeven {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int calculateNumLength(int k) {
        return (int) (Math.log((k + 1) / 2) / Math.log(2)) + 1;
    }

    private static String getKthNum(int K, int numLength) {
        String binaryK = Integer.toBinaryString(K);
        for (; binaryK.length() < numLength; )
            binaryK = "0" + binaryK;

        StringBuilder sb = new StringBuilder();
        for (char n : binaryK.toCharArray())
            sb.append(n == '0' ? "4" : "7");

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());
        int numLength = calculateNumLength(K);
        K = (int) (K - (2 * Math.pow(2, numLength - 1)) + 1);

        bw.write(getKthNum(K, numLength));
        bw.flush();
    }
}
