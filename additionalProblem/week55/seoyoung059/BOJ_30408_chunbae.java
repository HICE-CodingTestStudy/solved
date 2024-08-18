package fin.HICE.week55.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_30408_chunbae {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        ArrayDeque<Long> q = new ArrayDeque<>();
        Set<Long> set = new HashSet<>();
        q.offer(n); set.add(n);

        Long curr; boolean find = false;
        if(m==n) find = true;
        else {
            while (!q.isEmpty()) {
//                System.out.println("Arrays.toString(q.toArray() = " + Arrays.toString(q.toArray()));
                curr = q.pollFirst();
                if(curr == m) {
                    find = true;
                    break;
                }
                if(curr == 1) continue;
                if(!set.contains(curr/2)) {
                    set.add(curr / 2);
                    q.offer(curr / 2);
                }
                if (curr % 2 == 0) continue;
                set.add(curr/2+1);
                q.offer(curr/2+1);
            }
        }

        System.out.println(find?"YES":"NO");
    }
}