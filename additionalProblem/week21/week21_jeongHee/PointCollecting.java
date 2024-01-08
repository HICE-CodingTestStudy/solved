package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PointCollecting {
    //https://www.acmicpc.net/problem/7571
    //점 모으기
    static int N, M;
    static List<Integer> pointsX = new ArrayList<>();
    static List<Integer> pointsY = new ArrayList<>();

    //절댓값 함수의 특정 x좌표에서의 함숫값들의 합의 최소가 되게 하는 x는 y = |X-N|의 N들 중 중앙값이다
    //갑자기 기계학습 중간 문제가 퍼뜩 생각나서 겨우 풀음..
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pointsX.add(a);
            pointsY.add(b);
        }

        Collections.sort(pointsX);
        Collections.sort(pointsY);
        int medianX = pointsX.get(M / 2);
        int medianY = pointsY.get(M / 2);
        long ans = 0;
        for (int i = 0; i < M; i++) {
            ans += Math.abs(pointsX.get(i) - medianX);
            ans += Math.abs(pointsY.get(i) - medianY);
        }
        System.out.println(ans);
    }
}
