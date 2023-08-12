package binarySearch.jeongHee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NumberCard2 {
    //https://www.acmicpc.net/problem/10816
    //숫자 카드 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(hm.get(num)!=null)
                hm.put(num,hm.get(num)+1);
            else hm.put(num,1);
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(hm.get(num)!=null)
                sb.append(hm.get(num));
            else sb.append(0);
            sb.append(" ");
        }
        System.out.println(sb.toString());

    }
}
