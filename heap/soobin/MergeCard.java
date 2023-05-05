package soobin;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeCard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner scanner = new Scanner(br.readLine());
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        PriorityQueue<Long> queue = new PriorityQueue<>();
        scanner = new Scanner(br.readLine());
        while(scanner.hasNext())
            queue.add(scanner.nextLong());

        while(M-- > 0) {
            long merged = queue.poll() + queue.poll();
            queue.add(merged);
            queue.add(merged);
        }

        long answer = 0;
        while(!queue.isEmpty()) answer += queue.poll();
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
