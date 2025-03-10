import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Guard {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] SECTOR = {0, 0, 2, 3, 1};
    private static final int[][] MOVES = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    private static int[][] stores;
    private static int[] donggeun;
    private static int width, height, start;

    public static void main(String[] args) throws Exception {
        init();
        int answer = 0;
        for (int i = 0; i < stores.length; i++) {
            int left = move(stores[i][0], stores[i][1]);
            int right = 2 * (width + height) - left;
            answer += Math.min(left, right);
        }

        System.out.println(answer);
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());
        stores = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int sector = Integer.parseInt(st.nextToken());
            int point =  Integer.parseInt(st.nextToken());
            stores[i] = getPoint(sector, point);
        }

        st = new StringTokenizer(br.readLine());
        int sector = Integer.parseInt(st.nextToken());
        int point = Integer.parseInt(st.nextToken());
        donggeun = getPoint(sector, point);
        start = SECTOR[sector];
    }

    private static int[] getPoint(int sector, int point) {
        int x, y;
        if (sector == 3) x = 0;
        else if (sector == 4) x = width;
        else x = point;

        if (sector == 2) y = 0;
        else if (sector == 1) y = height;
        else y = height - point;

        return new int[] {x, y};
    }

    private static int move(int tx, int ty) {
        int dist = 0;
        int cx = donggeun[0], cy = donggeun[1], dir = start;

        while (cx != tx || cy != ty) {
            int nx = cx + MOVES[dir][0];
            int ny = cy + MOVES[dir][1];
            if (isInvalid(nx, ny)) {
                dir = (dir+1) % 4;
                nx = cx + MOVES[dir][0];
                ny = cy + MOVES[dir][1];
            }

            cx = nx;
            cy = ny;
            dist++;
        }

        return dist;
    }

    private static boolean isInvalid(int x, int y) {
        return x < 0 || x > width || y < 0 || y > height;
    }
}