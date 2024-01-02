import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Anagram {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Queue<String> pq = new PriorityQueue<>();

    private static int[] visited;
    private static int length;

    private static void parse() {
        try {
            char[] target = br.readLine().toCharArray();
            visited = new int[26];
            length = target.length;
            for (char c : target) visited[c - 'a']++;
        } catch (IOException e) {}
    }

    private static void print() {
        try {
            while (!pq.isEmpty()) {
                String anagram = pq.poll();
                bw.write(anagram + "\n");
            }
        } catch (IOException e) {}
    }

    private static void solution(String anagram) {
        if (anagram.length() == length) {
            pq.add(anagram);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (visited[i] == 0) continue;

            visited[i]--;
            anagram += String.valueOf((char) (i + 'a'));
            solution(anagram);
            anagram = anagram.substring(0, anagram.length() - 1);
            visited[i]++;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            parse();
            solution("");
            print();
        }

        bw.flush();
    }
}
