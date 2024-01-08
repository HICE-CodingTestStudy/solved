import java.io.*;
import java.util.Objects;

public class TwoBox {
    private static class Coordinate {
        long x, y;

        Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object obj) {
            return ((Coordinate) obj).x == x && ((Coordinate) obj).y == y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean isSameX(Coordinate other) {
            return this.x == other.x;
        }

        public boolean isSameY(Coordinate other) {
            return this.y == other.y;
        }

        public boolean isIncludedX(Coordinate left, Coordinate right) {
            return left.x <= this.x && this.x <= right.x;
        }

        public boolean isIncludedY(Coordinate left, Coordinate right) {
            return left.y <= this.y && this.y <= right.y;
        }
    }

    private static class Box {
        Coordinate leftTop, leftBottom, rightTop, rightBottom;

        Box(long leftBottomX, long leftBottomY, long rightTopX, long rightTopY) {
            this.leftBottom = new Coordinate(leftBottomX, leftBottomY);
            this.leftTop = new Coordinate(leftBottomX, rightTopY);
            this.rightBottom = new Coordinate(rightTopX, leftBottomY);
            this.rightTop = new Coordinate(rightTopX, rightTopY);
        }

        public boolean isPoint(Box other) {
            return this.rightTop.equals(other.leftBottom)
                    || this.rightBottom.equals(other.leftTop)
                    || this.leftTop.equals(other.rightBottom)
                    || this.leftBottom.equals(other.rightTop);
        }

        private boolean isLineWithX(Box other) {
            return (this.rightTop.isSameX(other.leftTop) || this.leftTop.isSameX(other.rightTop))
                    && (this.rightTop.isIncludedY(other.leftBottom, other.leftTop)
                    || other.leftTop.isIncludedY(rightBottom, rightTop));
        }

        private boolean isLineWithY(Box other) {
            return (this.rightTop.isSameY(other.leftBottom) || this.leftBottom.isSameY(other.leftTop))
                    && (this.leftBottom.isIncludedX(other.leftBottom, other.rightBottom)
                    || other.leftBottom.isIncludedX(leftBottom, rightBottom));
        }

        public boolean isLine(Box other) {
            return isLineWithX(other) || isLineWithY(other);
        }

        public boolean isFace(Box other) {
            return (other.leftTop.isIncludedX(this.leftTop, this.rightTop)
                    || other.rightTop.isIncludedX(this.leftTop, this.rightTop))
                    && (this.leftTop.isIncludedY(other.leftBottom, other.leftTop)
                    || this.leftBottom.isIncludedY(other.leftBottom, other.leftTop));
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static void printResult(String result) {
        try {
            bw.write(result);
            bw.flush();
        } catch (IOException e) {}
    }

    private static Box getBox() {
        try {
            String[] line = br.readLine().split(" ");
            long leftBottomX = Long.parseLong(line[0]);
            long leftBottomY = Long.parseLong(line[1]);
            long rightTopX = Long.parseLong(line[2]);
            long rightTopY = Long.parseLong(line[3]);
            return new Box(leftBottomX, leftBottomY, rightTopX, rightTopY);
        } catch (IOException e) {}
        return null;
    }

    public static void main(String[] args) {
        Box P = getBox();
        Box Q = getBox();

        if (P.isPoint(Q)) {
            printResult("POINT");
            return;
        }

        if (P.isLine(Q)) {
            printResult("LINE");
            return;
        }

        if (P.isFace(Q) || Q.isFace(P)) {
            printResult("FACE");
            return;
        }

        printResult("NULL");
    }
}
