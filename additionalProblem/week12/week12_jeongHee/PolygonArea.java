package algoStudy.week1;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PolygonArea {
    //https://www.acmicpc.net/problem/2166
    //다각형의 면적
    public static class Coordinate {
        double x;
        double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // n-2 개의 삼각형으로 쪼개어 구하기 -> 오목한 다각형의 경우 처리 불가

    //    public static double calculate(Coordinate a, Coordinate b, Coordinate c) {
//        double gradient = ((double) b.y - (double) c.y) / ((double) b.x - (double) c.y);
//        double coefficientY = -1.0;
//        if (b.x == c.x) {
//            return Math.abs(b.y - c.y) * Math.abs(b.x - a.x) * 0.5;
//        }
//        double constant = -1 * gradient * (double) b.x + (double) b.y;
//        double distanceBetweenPointAndLine = Math.abs(gradient * (double) a.x + coefficientY * (double) a.y + constant)
//                / Math.pow(gradient * gradient + coefficientY * coefficientY, 0.5);
//        double distanceBetweenPointAndPoint = Math.pow(Math.pow((b.x - c.x), 2) + Math.pow((b.y - c.y),2), 0.5);
//        return distanceBetweenPointAndPoint * distanceBetweenPointAndLine * 0.5;
//
//    }
//
//    public static BigDecimal solution(Queue<Coordinate> pq) {
//        Coordinate standard = pq.poll();
//        Coordinate first = pq.poll();
//        Coordinate second = pq.poll();
//        BigDecimal ans = BigDecimal.valueOf(calculate(standard, first, second));
//        while (!pq.isEmpty()) {
//            first = second;
//            second = pq.poll();
//            BigDecimal next = BigDecimal.valueOf(calculate(standard, first, second));
//            ans = ans.add(next);
//        }
//        return ans;
//    }
    public static double solution(Queue<Coordinate> pq) {
        Coordinate first = pq.peek();
        double[][] coordinates = new double[pq.size() + 1][2];
        coordinates[0][0] = first.x;
        coordinates[0][1] = first.y;
        coordinates[coordinates.length - 1][0] = first.x;
        coordinates[coordinates.length - 1][1] = first.y;
        pq.poll();
        for (int i = 1; i < coordinates.length - 1; i++) {
            coordinates[i][0] = pq.peek().x;
            coordinates[i][1] = pq.peek().y;
            pq.poll();
        }
        double x = 0;
        double y = 0;
        for (int i = 0; i < coordinates.length - 1; i++) {
            x +=  coordinates[i][0] *  coordinates[i+1][1];
            y +=  coordinates[i+1][0] *  coordinates[i][1];
        }
        return (Math.abs(x-y))*0.5;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            queue.add(new Coordinate(sc.nextInt(), sc.nextInt()));
        }
        System.out.printf("%.1f",solution(queue));
    }
}
