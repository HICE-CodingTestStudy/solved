package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HighTeenShark {
    //https://www.acmicpc.net/problem/19236
    //청소년 상어
    static long ans = 0;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    //이차원 배열에 물고기 번호를 저장
    static int[][] map = new int[4][4];

    //물고기 번호에 따른 이차원 배열 상의 좌표를 저장
    static Map<Integer, int[]> coordinate = new HashMap<>();

    //물고기 번호에 따른 방향 번호 저장
    static Map<Integer, Integer> direction = new HashMap<>();

    //특정 상어 좌표와, 상어 방향, 먹은 물고기 양을 가지고
    //다음으로 먹을 물고기를 전부 탐색하여 최대값 찾음
    static void solution(int[] shark, int d, long count) {
        moveFish(shark);
        //최대 3칸까지 건너뛸 수 있는 가능성이 있음
        for (int i = 0; i < 4; i++) {
            //다음으로 갈 상어 좌표
            int nextI = shark[0] + i * dx[d];
            int nextJ = shark[1] + i * dy[d];
            //유효성 검증
            if (nextI < 0 || nextI >= 4 || nextJ < 0 || nextJ >= 4) continue;
            if (map[nextI][nextJ] == 0) continue;
            int[][] tmpMap = new int[4][4];
            for (int j = 0; j < 4; j++) {
                tmpMap[j] = map[j].clone();
            }
            //이동하기 전에 기존 값 따로 저장
            Map<Integer, int[]> tmpCoordinate = new HashMap<>(coordinate);
            Map<Integer, Integer> tmpDirection = new HashMap<>(direction);
            int nextD = direction.get(map[nextI][nextJ]);
            direction.remove(map[nextI][nextJ]);
            coordinate.remove(map[nextI][nextJ]);
            int fish = map[nextI][nextJ];
            map[nextI][nextJ] = 0;
            //이동
            solution(new int[]{nextI, nextJ}, nextD, count + fish);
            //이동 후 이전으로 상태 되돌리기
            direction = tmpDirection;
            coordinate = tmpCoordinate;
            map = tmpMap;
        }
        //최대값 갱신
        ans = Math.max(count, ans);
    }

    //특정 상어 좌표가 있을 때 물고기를 이동시킴
    private static void moveFish(int[] shark) {
        for (int i = 1; i <= 16; i++) {
            //없는 번호일시 건너 뜀
            if (!coordinate.containsKey(i)) continue;
            //방향 반시계로 바꿔가며 가능하다면 물고기 이동
            for (int j = 0; j <= 7; j++) {
                int nextDirection = (direction.get(i) + j) % 8;
                //이동할 좌표
                int nextI = coordinate.get(i)[0] + dx[nextDirection];
                int nextJ = coordinate.get(i)[1] + dy[nextDirection];
                //유효성 검증
                if (nextI < 0 || nextJ < 0 || nextI >= 4 || nextJ >= 4) continue;
                if (nextI == shark[0] && nextJ == shark[1]) continue;
                //해당 번호의 물고기의 방향이 바뀌었으니 업데이트
                direction.put(i, nextDirection);
                //빈칸으로 이동할 시
                if (!coordinate.containsKey(map[nextI][nextJ])) {
                    //기존 물고기 위치를 빈칸으로 만듦
                    map[coordinate.get(i)[0]][coordinate.get(i)[1]] = 0;
                    map[nextI][nextJ] = i;
                    coordinate.put(i, new int[]{nextI, nextJ});
                    break;
                }
                //있는 물고기와 교환 시
                int[] tmp = coordinate.get(i);
                coordinate.put(map[nextI][nextJ], new int[]{coordinate.get(i)[0], coordinate.get(i)[1]});
                coordinate.put(i, new int[]{nextI, nextJ});
                map[tmp[0]][tmp[1]] = map[nextI][nextJ];
                map[nextI][nextJ] = i;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = a;
                coordinate.put(a, new int[]{i, j});
                direction.put(a, b);
            }
        }
        int[] shark = coordinate.get(map[0][0]);
        int d = direction.get(map[0][0]);
        int init = map[0][0];
        coordinate.remove(init);
        direction.remove(init);
        map[0][0] = 0;
        ans = init;
        //0,0 의 물고기를 먹은 채 시작
        solution(shark, d, init);
        System.out.println(ans);
    }
}
