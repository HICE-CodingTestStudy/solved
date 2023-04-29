package soobin;

import java.io.*;
import java.util.PriorityQueue;

public class SortCard {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));

    private static void write(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        if(N == 1) {
            br.readLine();
            write(0);
            return;
        }

        for(int i = 0; i < N; i++)
            queue.add(Integer.parseInt(br.readLine()));

        while(!queue.isEmpty()) {
            int pile1 = queue.poll();
            int pile2 = queue.poll();
            answer += pile1 + pile2;
            if(!queue.isEmpty()) queue.add(pile1 + pile2);
        }
        write(answer);
    }
}
