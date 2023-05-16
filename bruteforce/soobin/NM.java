package soobin;

import java.io.*;
import java.util.*;

public class NM {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(br.readLine());
        int N = sc.nextInt();
        int M = sc.nextInt();

        numbers = new int[N];
        boolean[] used = new boolean[N];
        List<Integer> generated = new ArrayList<>();
        for (int i = 0; i < N; i++) numbers[i] = i + 1;

        generate(generated, used, M);
        bw.flush();
    }

    private static void generate(List<Integer> generated, boolean[] used, int length) throws IOException {
        if (generated.size() == length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) sb.append(generated.get(i)).append(" ");
            bw.write(sb.toString());
            bw.newLine();
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (used[i]) continue;

            generated.add(numbers[i]);
            used[i] = true;
            generate(generated, used, length);
            used[i] = false;
            generated.remove(generated.size() - 1);
        }
    }
}
