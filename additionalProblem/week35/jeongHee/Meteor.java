package stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Meteor {
    static int R, S;
    static int downCount;
    static int[] meteorEndIndexes, landStartIndexes;
    static char[][] map, result;

    static void getDownCount() {
        for (int i = 0; i < S; i++) {
            //유성이 없는 경우
            if(meteorEndIndexes[i]==-1) continue;
            downCount = Math.min(downCount, (landStartIndexes[i] - meteorEndIndexes[i]) - 1);
        }
    }

    static void solution() {
        getDownCount();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                result[i][j] = '.';
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                if(map[i][j]=='#'){
                    result[i][j] = map[i][j];
                    continue;
                }
                if(map[i][j]=='X'){
                    result[i+downCount][j] = map[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new char[R][S];
        result = new char[R][S];
        meteorEndIndexes = new int[S];
        landStartIndexes = new int[S];
        downCount = R;
        Arrays.fill(meteorEndIndexes, -1);
        Arrays.fill(landStartIndexes, R - 1);
        for (int i = 0; i < R; i++) {
            String s = bf.readLine();
            for (int j = 0; j < S; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'X') {
                    meteorEndIndexes[j] = Math.max(meteorEndIndexes[j], i);
                }
                if (map[i][j] == '#') {
                    landStartIndexes[j] = Math.min(landStartIndexes[j], i);
                }
            }
        }
        solution();
    }
}
