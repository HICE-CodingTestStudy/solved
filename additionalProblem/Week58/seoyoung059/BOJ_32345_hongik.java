package Ing.Week58.seoyoung059;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_32345_hongik {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str; int tmp; boolean no;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            ArrayDeque<Integer> list = new ArrayDeque<>();
            no = false;
            str = br.readLine();
            tmp = 0;
            for(int j = 0; j < str.length(); j++){
                if("aeiou".indexOf(str.charAt(j))==-1){
                    tmp++;
                } else {
                    no = true;
                    if(tmp!=0){
                        list.offerLast(tmp);
                        tmp = 0;
                    }
                }
            }

            long answer;
            if(no) {
                if (tmp > 0) list.offerLast(tmp);
                answer = 1;
                if ("aeiou".indexOf(str.charAt(0)) == -1) {
                    list.pollFirst();
                }
                if ("aeiou".indexOf(str.charAt(str.length() - 1)) == -1) {
                    list.pollLast();
                }
                for (Integer l : list) {
                    answer *= ((l + 1)%1_000_000_007);
                    answer %= 1_000_000_007;
                }
            } else answer = -1;
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
