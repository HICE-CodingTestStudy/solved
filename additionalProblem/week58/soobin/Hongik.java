import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Hongik {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long MOD = 1000000007;

    /*
        university -> u/nivers/ity
        n[i]v[e]rs -> 2 * 2 * 3 = 12
        n/i/vers, n/i/ve/rs, n/i/ver/s
        n/iv/ers, n/iv/er/s, n/iv/e/rs
        ni/vers, ni/ve/rs, ni/ver/s
        niv/ers, niv/er/s, niv/e/rs
     */

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String word = br.readLine();
            int firstIdx = 0;
            for (; firstIdx < word.length(); firstIdx++) {
                if (isVowel(word.charAt(firstIdx))) break;
            }

            if (firstIdx == word.length()) {
                bw.write("-1\n");
                continue;
            }

            long count = 0, total = 1;
            for (int i = firstIdx + 1; i < word.length(); i++) {
                if (isVowel(word.charAt(i))) {
                    total = (total * (count + 1)) % MOD;
                    count = 0;
                } else count++;
            }
            bw.write((total % MOD) + "\n");
        }
        bw.flush();
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i';
    }
}
