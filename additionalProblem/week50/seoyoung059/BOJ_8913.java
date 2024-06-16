package ing.week50.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_8913 {

    static HashMap<String, Boolean> hm = new HashMap<>();

    static boolean solve(String str) {
        int cnt;
        String nextStr;

        switch (str.length()) {
            case 0:
                return true;
            case 1:
                return false;
            default:
                if (hm.get(str) != null) return hm.get(str);
                cnt = 1;
                for (int i = 1; i < str.length(); i++) {
                    if (str.charAt(i - 1) == str.charAt(i)) {
                        cnt++;
                    } else {
                        if (cnt >= 2) {
                            nextStr = str.substring(0, i - cnt).concat(str.substring(i));
                            hm.put(nextStr, solve(nextStr));
                            if (hm.get(nextStr)) return true;
                        }
                        cnt = 1;
                    }
                }
                if (cnt >= 2) {
                    nextStr = str.substring(0, str.length() - cnt);
                    hm.put(nextStr, solve(nextStr));
                    if (hm.get(nextStr)) return true;
                }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            if (solve(str)) {
                sb.append(1);
            } else sb.append(0);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
