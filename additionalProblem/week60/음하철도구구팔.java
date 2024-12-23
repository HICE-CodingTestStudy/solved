import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/1393
    //음하철도 구구팔
    static int xs, ys, xe, ye, dx, dy;

    static int calcDist(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    static void init() {
        for (int i = 2; i <= 100; i++) {
            while (dx % i == 0 && dy % i == 0) {
                dx /= i;
                dy /= i;
            }
        }
    }

    static void solution() {
        init();
        int ansX = xe, ansY = ye;
        int dist = calcDist(xs, ys, xe, ye);
        while (true) {
            int nDist = calcDist(xs, ys, ansX+dx, ansY+dy);
            if (dist < nDist) break;
            ansX += dx;
            ansY += dy;
            dist = nDist;
        }
        System.out.println(ansX + " " + ansY);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        xs = Integer.parseInt(st.nextToken());
        ys = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        xe = Integer.parseInt(st.nextToken());
        ye = Integer.parseInt(st.nextToken());
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());
        solution();
    }
}