import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dragon {
    static Map<Integer, Integer> direction = new HashMap<>();
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i <= 100 && j <= 100;
    }

    static void draw(int x, int y, int d, int g) {
        List<Integer> dragon = new ArrayList<>();
        dragon.add(d);
        map[x][y] = true;
        for (int i = 0; i < g; i++) {
            List<Integer> next = new ArrayList<>(dragon);
            for (int j = dragon.size() - 1; j >= 0; j--) {
                next.add(direction.get(dragon.get(j)));
            }
            dragon = next;
        }
        int nX = x;
        int nY = y;
        for (Integer i : dragon) {
            nX += dx[i];
            nY += dy[i];
            map[nX][nY] = true;
        }
    }

    static int calcAns() {
        int ans = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!isValid(i + 1, j + 1)) continue;
                if(map[i][j]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        direction.put(1, 2);
        direction.put(0, 1);
        direction.put(2, 3);
        direction.put(3, 0);

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            draw(y, x, d, g);
        }
        System.out.println(calcAns());
    }
}
