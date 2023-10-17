package week17.soobin;

import java.io.*;
import java.util.*;

public class LectureRoom {
    private static class Lecture {
        int id, start, end;

        Lecture(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Lecture> lectures;

    private static void parseInput() {
        lectures = new ArrayList<>();

        try {
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int id = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                lectures.add(new Lecture(id, start, end));
            }
        } catch (IOException e) {}

        Collections.sort(lectures, Comparator.comparingInt(o -> o.start));
    }

    private static void printOutput(int numRoom) {
        try {
            bw.write(String.valueOf(numRoom));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int countMinRoom() {
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        int numRoom = 0;

        for (Lecture lecture : lectures) {
            while (!endTimes.isEmpty() && endTimes.peek() <= lecture.start)
                endTimes.poll();

            endTimes.add(lecture.end);
            numRoom = Math.max(numRoom, endTimes.size());
        }

        return numRoom;
    }

    public static void main(String[] args) {
        parseInput();
        int numRoom = countMinRoom();
        printOutput(numRoom);
    }
}
