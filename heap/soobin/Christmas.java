package soobin;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Christmas {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> presents = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            Scanner scanner = new Scanner(br.readLine());
            int a = scanner.nextInt();

            if (a == 0) {
                int worth = presents.isEmpty() ? -1 : presents.poll();
                bw.write(String.format("%d\n", worth));
            } else
                while (scanner.hasNext()) presents.add(scanner.nextInt());
        }
        bw.flush();
    }
}
