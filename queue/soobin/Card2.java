package soobin;

import java.io.*;
import java.util.*;

public class Card2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        Queue<Integer> queue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++) queue.add(i);
        while(queue.size() > 1) {
            queue.poll();
            int toBottom = queue.poll();
            queue.add(toBottom);
        }

        bw.write(String.valueOf(queue.poll()));
        bw.newLine();
        bw.flush();
    }
}
