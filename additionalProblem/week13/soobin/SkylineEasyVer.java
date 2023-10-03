package week13.soobin;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class SkylineEasyVer {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Stack<Integer> buildings = new Stack<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int y = Integer.parseInt(st.nextToken());

            if (y > 0 && (buildings.isEmpty() || buildings.peek() < y)) {
                buildings.push(y);
                continue;
            }

            while (!buildings.isEmpty() && buildings.peek() > y){
                buildings.pop();
                count++;
            }

            if (y > 0 && (buildings.isEmpty() || buildings.peek() != y))
                buildings.push(y);
        }
        count += buildings.size();

        bw.write(String.valueOf(count));
        bw.flush();
    }
}
