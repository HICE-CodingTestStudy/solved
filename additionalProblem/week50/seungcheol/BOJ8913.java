package week50;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BOJ8913 {
    static boolean isAvailable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            isAvailable = false;
            String input = br.readLine() + " ";
            //각 그룹에 속한 알파벳 개수 aabbaabb -> 2 2 2 2
            List<Integer> counts = new ArrayList<>();
            for (int j = 0; j < input.length(); j++) {
                int cnt = 1;
                for (int k = j; k < input.length() - 1; k++) {
                    if (input.charAt(k) != input.charAt(k + 1)) {
                        counts.add(cnt);
                        j = k;
                        break;
                    }
                    cnt++;
                }
            }
            dfs(counts);
            sb.append(isAvailable ? 1 : 0).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(List<Integer> list) {
        if(isAvailable) return;
        if (list.size() == 1) {
            isAvailable = list.get(0) >= 2;
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            //2보다 작으면 스킵
            if(isAvailable) break;
            if (list.get(i) < 2) continue;
            List<Integer> next = new ArrayList<>();
            for (int j = 0; j < i - 1; j++) {
                next.add(list.get(j));
            }
            //현재 그룹을 삭제하면 앞 뒤가 연결되어야 함
            int front = (0 <= i - 1 ? list.get(i - 1) : 0);
            int union = front + (list.size() > i + 1 ? list.get(i + 1) : 0);
            next.add(union);
            for (int j = i + 2; j < list.size(); j++) {
                next.add(list.get(j));
            }
            dfs(next);
        }
    }
}
