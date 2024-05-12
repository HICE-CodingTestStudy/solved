import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class TravelingLecture {
    private static class Lecture implements Comparable {
        int dueDate, price;

        Lecture(int dueDate, int price) {
            this.dueDate = dueDate;
            this.price = price;
        }

        @Override
        public int compareTo(Object o) {
            return ((Lecture) o).price - this.price;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Queue<Lecture> lectures = new PriorityQueue<>();
    private static final int[] lectureByDate = new int[10001];

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int price = Integer.parseInt(input[0]);
            int dueDate = Integer.parseInt(input[1]);
            lectures.add(new Lecture(dueDate, price));
        }
    }

    private static int solution() {
        int answer = 0;

        while (!lectures.isEmpty()) {
            Lecture lecture = lectures.poll();
            int date = lecture.dueDate;
            while (date > 0 && lectureByDate[date] > 0) date--;
            if (date == 0) continue;

            lectureByDate[date] = lecture.price;
            answer += lecture.price;
        }

        return answer;
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
