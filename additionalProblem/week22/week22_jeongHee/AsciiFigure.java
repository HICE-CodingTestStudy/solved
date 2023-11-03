package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AsciiFigure {
    //https://www.acmicpc.net/problem/3495
    //아스키 도형
    static int h, w;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        for (int i = 0; i < h; i++) {
            String s = bf.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        double ans = 0;
        boolean isOpen = false;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] != '.') {
                    ans += 0.5;
                    isOpen = !isOpen;
                }
                else if(isOpen) ans += 1;
            }
        }
        System.out.printf("%.0f",ans);
    }
}
