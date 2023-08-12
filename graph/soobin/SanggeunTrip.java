package soobin;

import java.io.*;
import java.util.*;

public class SanggeunTrip {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            Scanner sc = new Scanner(br.readLine());
            int N = sc.nextInt(); int M = sc.nextInt();

            for (int i = 0; i < M; i++)
                sc = new Scanner(br.readLine());

            bw.write(String.valueOf(N-1));
            bw.newLine();
        }
        bw.flush();
    }
}