import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Star {
    private static class Coordinate {
        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isHit(int xs, int ys, int xe, int ye) {
            return x < xs || x > xe || y < ys || y > ye;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Coordinate> stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Coordinate(x, y));
        }

        int max = 0;
        for (Coordinate first : stars) {
            for (Coordinate second : stars) {
                int xStart = first.x, yStart = second.y;
                int xEnd = xStart + L, yEnd = yStart + L;

                int nBlocked = 0;
                for (Coordinate star : stars) {
                    if (!star.isHit(xStart, yStart, xEnd, yEnd)) nBlocked++;
                }

                max = Math.max(max, nBlocked);
            }
        }

        System.out.println(stars.size() - max);
    }
}
