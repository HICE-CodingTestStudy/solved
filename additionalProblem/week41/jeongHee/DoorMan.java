import java.io.*;
import java.util.ArrayDeque;

public class Main {
    static ArrayDeque<Character> deque = new ArrayDeque<>();
    static String input;
    static int X, man, woman, ans;

    static boolean solution(char gender, int first, int second) {
        first++;
        if (Math.abs(first - second) > X && !deque.isEmpty()) {
            char next = deque.peek();
            first--;
            if (next != gender) {
                deque.poll();
                deque.offerFirst(gender);
                second++;
            } else {
                ans = first + second;
                return false;
            }
        } else if (Math.abs(first - second) > X && deque.isEmpty()) {
            first--;
            ans = first + second;
            return false;
        }
        if (gender == 'M') {
            man = first;
            woman = second;
        } else {
            man = second;
            woman = first;
        }
        ans = man + woman;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        X = Integer.parseInt(br.readLine());
        input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            deque.offerLast(input.charAt(i));
        }
        while (!deque.isEmpty()) {
            char gender = deque.poll();
            if (gender == 'M') {
                if (!solution(gender, man, woman)) break;
            } else {
                if (!solution(gender, woman, man)) break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(woman + man);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}