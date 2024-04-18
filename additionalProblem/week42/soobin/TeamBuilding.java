import java.io.*;

public class TeamBuilding {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] developers;
    private static int N;

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            developers = new int[N];

            String[] input = br.readLine().split(" ");
            for (int i = 0; i < N; i++)
                developers[i] = Integer.parseInt(input[i]);
        } catch (IOException ignored) {}
    }

    private static int solution() {
      int answer = 0, start = 0, end = N - 1;

      while (start + 1 < end) {
          answer = Math.max(answer, calcAbility(start, end));
          int diff = developers[start] - developers[end];
          if (diff < 0) start++;
          else if (diff > 0) end--;
          else {
              start++;
              end--;
          }
      }

      return answer;
    }

    private static int calcAbility(int start, int end) {
        int between = end - start - 1;
        int min = Math.min(developers[start], developers[end]);
        return between * min;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
