import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GalaxyTrain {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int xs, ys;
    private static int xe, ye, dx, dy;

    public static void main(String[] args) throws Exception {
        init();
        if (dx == 0 || dy == 0) {
            if (dx == 0 && dy == 0) {
                System.out.println(xe + " " + ye);
            } else if (dx == 0) {
                System.out.println(xe + " " + ys);
            } else {
                System.out.println(xs + " " + ye);
            }
            return;
        }

        double dist = calcDist(xs, ys, xe, ye);
        int x = xe, y = ye;
        while (true) {
            int nextX = x + 1, nextY = getY(nextX);
            if (nextY == Integer.MIN_VALUE) {
                x = nextX;
                continue;
            }

            double d = calcDist(xs, ys, nextX, nextY);
            if (dist < d) {
                x = getX(x, y);
                break;
            }

            dist = d;
            x = nextX;
            y = nextY;
        }

        System.out.println(x + " " + y);
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        xs = Integer.parseInt(st.nextToken());
        ys = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        xe = Integer.parseInt(st.nextToken());
        ye = Integer.parseInt(st.nextToken());
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());
    }

    private static int getX(int x, int y) {
        if (dy == 0) return x;
        return (dx * y + dy * xe - dx * ye) / dy;
    }

    private static int getY(int x) {
        double y = (double) (dy * x + dx * ye - dy * xe) / dx;
        if (y != Math.floor(y)) return Integer.MIN_VALUE;
        return (int) y;
    }

    private static double calcDist(int fromX, int fromY, int toX, int toY) {
        int dx = fromX - toX;
        int dy = fromY - toY;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
