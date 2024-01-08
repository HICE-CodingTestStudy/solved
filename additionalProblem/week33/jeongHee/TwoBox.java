import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //https://www.acmicpc.net/problem/15973
    //두 박스
    static class Coordinate {
        long x, y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Coordinate c) {
            return x == c.x && y == c.y;
        }
    }

    static class Box {
        Coordinate lu, ld, ru, rd;

        public Box(Coordinate lu, Coordinate ld, Coordinate ru, Coordinate rd) {
            this.lu = lu;
            this.ld = ld;
            this.ru = ru;
            this.rd = rd;
        }
    }

    static List<Box> boxes = new ArrayList<>();

    static boolean checkA(Box fir, Box sec) {
        if (fir.ld.isSame(sec.ru) || fir.rd.isSame(sec.lu)) {
            System.out.println("POINT");
            return true;
        }
        return false;
    }

    static boolean checkB(Box fir, Box sec) {
        if (fir.ld.x == sec.rd.x || fir.rd.y == sec.lu.y) {
            System.out.println("LINE");
            return true;
        }
        return false;
    }

    static boolean checkE(Box fir, Box sec) {
        if (fir.ld.y > sec.lu.y || fir.rd.x < sec.ld.x) {
            System.out.println("NULL");
            return true;
        }
        return false;
    }

    static void solution() {
        Box fir = boxes.get(0);
        Box sec = boxes.get(1);
        if (checkA(fir, sec) || checkA(sec, fir)) return;
        if (checkE(fir, sec) || checkE(sec, fir)) return;
        if (checkB(fir, sec) || checkB(sec, fir)) return;
        System.out.println("FACE");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            boxes.add(new Box(
                    new Coordinate(x1, y2),
                    new Coordinate(x1, y1),
                    new Coordinate(x2, y2),
                    new Coordinate(x2, y1)
            ));
        }
        solution();
    }
}
