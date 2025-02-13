package jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Arm {
    // https://www.acmicpc.net/problem/1105
    // 팔
    static String start, end;
    static int solution() {
        if(start.length()!=end.length()) return 0;
        int count = 0;
        for (int i = 0; i < start.length() ; i++) {
            int s = start.charAt(i) - '0';
            int e = end.charAt(i) - '0';
            if(s!=e) return count;
            if(s==8) count++;
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        start = st.nextToken();
        end = st.nextToken();
        System.out.println(solution());
    }
}
