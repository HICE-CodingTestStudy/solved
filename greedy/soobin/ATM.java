package soobin;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ATM {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Scanner sc = new Scanner(br.readLine());
        int[] persons = new int[N];

        for (int i = 0; i < N; i++) persons[i] = sc.nextInt();
        Arrays.sort(persons);

        int[] waiting = new int[N];
        int totalWaiting = waiting[0] = persons[0];
        for (int i = 1; i < N; i++) {
            waiting[i] = waiting[i - 1] + persons[i];
            totalWaiting += waiting[i];
        }

        bw.write(String.valueOf(totalWaiting));
        bw.flush();
    }
}
