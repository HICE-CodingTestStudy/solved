package week2.soobin;

import java.io.*;
import java.util.*;

public class FashionKing {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            Map<String, Integer> total = new HashMap<>();
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cloth = st.nextToken();
                String category = st.nextToken();
                total.put(category, total.getOrDefault(category, 0) + 1);
            }

            int answer = 1;
            for (int cloth : total.values()) {
                answer *= cloth + 1;
            }
            bw.write(String.format("%d\n", answer - 1));
        }
        bw.flush();
    }
}
