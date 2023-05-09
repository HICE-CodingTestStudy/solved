package soobin;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Words {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < N; i++) wordSet.add(br.readLine());
        List<String> words = wordSet.stream().collect(Collectors.toList());

        Collections.sort(words, (o1, o2) -> {
            if (o1.length() != o2.length()) return o1.length() < o2.length() ? -1 : 1;
            else return o1.compareTo(o2);
        });

        for (String word : words) {
            bw.write(word);
            bw.newLine();
        }
        bw.flush();
    }
}
