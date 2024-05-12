import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyberOT {
    //https://www.acmicpc.net/problem/19583
    //사이버 개강총회
    static int s, e, q;
    static int ans;
    static Set<String> in = new HashSet<>(), out = new HashSet<>();

    static void solution(String sTime, String name) {
        int time = stringToInt(sTime);
        if (time <= s) in.add(name);
        if (time >= e && time <= q) out.add(name);
    }

    static int stringToInt(String s) {
        String[] strs = s.split(":");
        String newS = strs[0] + strs[1];
        return Integer.parseInt(newS);
    }

    static void countAns() {
        for (String s : in) {
            if (out.contains(s)) ans++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        s = stringToInt(st.nextToken());
        e = stringToInt(st.nextToken());
        q = stringToInt(st.nextToken());
        while (true) {
            String next = bf.readLine();
            if(next==null || next.equals("")) break;
            st = new StringTokenizer(next);
            solution(st.nextToken(), st.nextToken());
        }
        countAns();
        System.out.println(ans);
    }
}
