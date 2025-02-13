import java.io.BufferedReader;
import java.io.InputStreamReader;

class Fortune {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] alphabets;
    private static int originLength, answer;

    public static void main(String[] args) throws Exception {
        init();
        arrange(-1, 0);
        System.out.println(answer);
    }

    private static void init() throws Exception {
        String origin = br.readLine();
        originLength = origin.length();
        alphabets = new int[26];

        for (char c : origin.toCharArray()) {
            alphabets[c-'a']++;
        }
    }

    private static void arrange(int prev, int length) {
        if (length == originLength) {
            answer++;
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (i == prev || alphabets[i] == 0) continue;
            alphabets[i]--;
            arrange(i, length+1);
            alphabets[i]++;
        }
    }
}