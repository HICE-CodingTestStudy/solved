package ing.week57.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1484_diet {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());

        int prev = 1;
        int curr = 2;

        ArrayList<Integer> list = new ArrayList<>();

        int tmp = curr*curr - prev*prev;
        while (true) {
            if(tmp == g) {
                list.add(curr);
                curr++;
            } else if(prev==curr-1 && tmp > 100_000){
                break;
            }else if (tmp > g) {
                if(prev < curr-1) prev++;
                else curr++;
            } else {
                curr++;
            }
            tmp = curr*curr - prev*prev;
        }

        StringBuilder sb = new StringBuilder();

        if(list.isEmpty()) {
            sb.append("-1");
        } else {
            for (Integer integer : list) {
                sb.append(integer).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
