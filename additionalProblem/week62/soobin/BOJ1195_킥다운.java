import java.io.BufferedReader;
import java.io.InputStreamReader;

class KickDown {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        String first = br.readLine();
        String second = br.readLine();

        int minLength = combine(first, second);
        minLength = Math.min(minLength, combine(second, first));

        System.out.println(minLength);
    }

    private static int combine(String first, String second) {
        int minLength = first.length() + second.length();
        for (int s = 0; s < first.length(); s++) {
            if (!isValid(second, first, s)) continue;

            int length = s + second.length();
            if (length < first.length()) length = first.length();

            minLength = Math.min(minLength, length);
        }

        return minLength;
    }

    private static boolean isValid(String shortGear, String longGear, int s) {
        for (int i = s; i < longGear.length() && (i-s) < shortGear.length(); i++) {
            if (shortGear.charAt(i-s) == '2' && longGear.charAt(i) == '2') return false;
        }
        return true;
    }
}