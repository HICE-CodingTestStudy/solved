package ing.week50.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_23326 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int current = 0;

        TreeSet<Integer> ts = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (st.nextToken().charAt(0) == '1')
                ts.add(i);
        }

        StringBuilder sb = new StringBuilder();
        Integer tmp;
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    tmp = Integer.parseInt(st.nextToken()) - 1;
                    if (!ts.remove(tmp)) {
                        ts.add(tmp);
                    }
                    break;
                case 2:
                    current = (current + Integer.parseInt(st.nextToken())) % N;
                    break;
                case 3:
                    if (ts.size() == 0) {
                        sb.append(-1);
                    } else {
                        tmp = ts.ceiling(current);
                        if (tmp == null) {
                            sb.append(ts.first() + N - current);
                        } else {
                            sb.append(tmp - current);
                        }
                    }
                    sb.append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
