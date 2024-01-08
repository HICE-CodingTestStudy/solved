package queue;

import java.util.Arrays;
import java.util.Scanner;

public class OverWatch {
    public static class Coordinate implements Comparable<Coordinate> {
        int index;
        int x;
        int y;
        int v;
        double speed;

        public Coordinate(int index, int x, int y, int v) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.v = v;
            this.speed = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5) / (double) v;
        }

        @Override
        public int compareTo(Coordinate o) {
            if (o.speed != this.speed)
                return speed - o.speed > 0 ? 1 : -1;
            return index - o.index;
        }
    }

    //https://www.acmicpc.net/problem/13411
    //하늘에서 정의가 빗발친다
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Coordinate[] coordinates = new Coordinate[N];
        for (int i = 0; i < N; i++) {
            coordinates[i] = new Coordinate(i+1, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(coordinates);
        for (Coordinate coordinate : coordinates) {
            System.out.println(coordinate.index);
        }
    }
}
