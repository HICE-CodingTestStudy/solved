package week4.soobin;

import java.io.*;
import java.util.*;

public class ChickenDelivery {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<int[]> houses;
    private static List<int[]> chickens;
    private static int minDist = Integer.MAX_VALUE;

    private static int calDistance(int[] house, int[] chicken) {
        return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
    }

    private static void selectChicken(Stack<int[]> selected, int i, int M) {
        if (M > 0) {
            for (int j = i; j < chickens.size(); j++) {
                selected.push(chickens.get(j));
                selectChicken(selected, j + 1, M - 1);
                selected.pop();
            }
            return;
        }

        int total = 0;
        for (int[] house : houses) {
            int min = Integer.MAX_VALUE;
            for (int[] chicken : selected) {
                int dist = calDistance(house, chicken);
                min = Math.min(min, dist);
            }
            total += min;
        }
        minDist = Math.min(minDist, total);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char type = st.nextToken().charAt(0);

                if (type == '1') houses.add(new int[] {i, j});
                if (type == '2') chickens.add(new int[] {i, j});
            }
        }

        Stack<int[]> selected = new Stack<>();
        for (int i = 0; i < chickens.size(); i++) {
            selected.push(chickens.get(i));
            selectChicken(selected, i + 1, M - 1);
            selected.pop();
        }

        bw.write(String.valueOf(minDist));
        bw.flush();
    }
}
