import java.io.*;

public class Square {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, length;

    public static void main(String[] args) {
        parseInput();
        int square = getNSquare();
        if (N > 0) square += getAdditionalSquare();
        printAnswer(square);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
        } catch (IOException ignored) {}
    }

    private static int getNSquare() {
        int square = 0;
        length = (int) Math.sqrt(N);

        for (int i = 1; i < length; i++) {
           square += i * i;
        }
        N -= length * length;

        return square;
    }

    private static int getAdditionalSquare() {
        int square = 0, i = 1;
        if (N <= length)
            while (i <= N - 1) square += i++;
        else {
            N -= length;
            while (i <= length - 1) square += i++;
            i = 1;
            while (i <= N - 1) square += i++;
        }
        return square;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
