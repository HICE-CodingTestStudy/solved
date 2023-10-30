package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WizardSharkAndTornado {
    //https://www.acmicpc.net/problem/20057
    //마법사 상어와 토네이도
    static int[][] ratio; //퍼지는 비율을 예시그림 그대로 저장함 (알파자리에는 -1저장)
    static int[][] map;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int ans = 0;

    static void init() {
        ratio = new int[][]
                {
                        {0, 0, 2, 0, 0},
                        {0, 10, 7, 1, 0},
                        {5, -1, 0, 0, 0},
                        {0, 10, 7, 1, 0},
                        {0, 0, 2, 0, 0}
                };

    }

    //비율판을 반시계방향으로 회전한다
    static void rotate() {
        int[][] tmpRatio = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmpRatio[4 - j][i] = ratio[i][j];
            }
        }
        ratio = tmpRatio;
    }

    //알파에 해당하는 값을 계산해준다
    static int calcAlpha(int totalSand) {
        int left = totalSand;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (ratio[k][l] == -1 || ratio[k][l] == 0) continue;
                left -= totalSand * ratio[k][l] / 100;
            }
        }
        return left;
    }

    //i, j = 예시상의 x
    //nextI, nextJ = 예시상의 y
    static void move(int i, int j, int direction, int sand) {
        int nextI = i + dx[direction];
        int nextJ = j + dy[direction];
        int totalSand = map[nextI][nextJ] + sand;
        //해당 칸에 도달했을때 비율판을 참고하여(5*5) 퍼트려준다
        //y기준으로 왼쪽 두칸, 오른쪽 두칸이므로 k, l의 범위는 다음과 같음
        for (int k = -2; k <= 2; k++) {
            int ratioI = k + 2;
            for (int l = -2; l <= 2; l++) {
                int ratioJ = l + 2;
                int spread = totalSand * ratio[ratioI][ratioJ] / 100;
                if (ratio[ratioI][ratioJ] == -1) spread = calcAlpha(totalSand);
                //벗어나면 흩어진 양 만큼 답에 더해준다
                if (nextI + k < 0 || nextI + k >= N || nextJ + l < 0 || nextJ + l >= N) {
                    ans += spread;
                    continue;
                }
                //벗어나지 않으면 해당 칸에 쌓아준다
                map[nextI + k][nextJ + l] += spread;
            }
        }
        //흩어지고 난 뒤 해당 칸에는 모래가 없어진다
        map[nextI][nextJ] = 0;
    }

    static void solution() {
        int I = N / 2;
        int J = N / 2;
        int direction = 0;
        //길이 1 ~ N-2까지는 2번, 길이 N-1은 3번 돈다
        // 1칸 -> 1칸 -> 2칸 -> 2칸 -> . . . N-1칸 *3 만큼 이동하고 끝
        for (int i = 1; i < N; i++) {
            if (i != N - 1) {
                for (int j = 0; j < 2; j++) {
                    //i 칸의 직선주로를 지나고
                    for (int k = 0; k < i; k++) {
                        move(I, J, direction, map[I][J]);
                        I += dx[direction];
                        J += dy[direction];
                    }
                    //회전한다
                    rotate();
                    direction = direction == 3 ? 0 : direction + 1;
                }
                continue;
            }
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < i; k++) {
                    move(I, J, direction, map[I][J]);
                    I += dx[direction];
                    J += dy[direction];
                }
                rotate();
                direction = direction == 3 ? 0 : direction + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        init();
        solution();
        System.out.println(ans);

    }
}
