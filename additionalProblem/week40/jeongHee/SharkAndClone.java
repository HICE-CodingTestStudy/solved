import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SharkAndClone {
    //https://www.acmicpc.net/problem/23290
    //마법사 상어와 복제
    static int S, simulCount;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sdx = {-1, 0, 1, 0}, sdy = {0, -1, 0, 1};
    static Queue<Scent> scentQueue = new ArrayDeque<>();
    static HashMap<String, Integer> scentMap = new HashMap<>();
    static List<Fish>[][] map = new ArrayList[4][4], clonedFish;
    static List<int[]> sharkDirList = new ArrayList<>();
    static Fish shark;

    static class Scent {
        int i, j;
        int time;

        public Scent(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }

        public String getKey() {
            return i + " " + j;
        }
    }

    static class Fish {
        int i, j;
        int d;

        public Fish(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < 4 && j < 4;
    }

    static void initSharkDirList(int count, int[] arr) {
        if (count == 3) {
            sharkDirList.add(arr.clone());
            return;
        }
        for (int i = 0; i < 4; i++) {
            arr[count] = i;
            initSharkDirList(count + 1, arr);
        }
    }

    static void cloneFish() {
        clonedFish = new List[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                clonedFish[i][j] = map[i][j];
            }
        }
    }

    static void moveFish() {
        List<Fish>[][] tmpMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmpMap[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    Fish fish = map[i][j].get(k);
                    boolean canMove = false;
                    for (int l = 0; l < 8; l++) {
                        int nextI = fish.i + dx[(fish.d - l + 8) % 8];
                        int nextJ = fish.j + dy[(fish.d - l + 8) % 8];
                        if (!isValid(nextI, nextJ)) continue;
                        if (shark.i == nextI && shark.j == nextJ) continue;
                        if (scentMap.containsKey(nextI + " " + nextJ)) continue;
                        tmpMap[nextI][nextJ].add(new Fish(nextI, nextJ, (fish.d - l + 8) % 8));
                        canMove = true;
                        break;
                    }
                    if (!canMove) tmpMap[fish.i][fish.j].add(fish);
                }
            }
        }
        map = tmpMap;
    }

    static void moveShark() {
        int choice = -1;
        int canEat = -1;
        for (int i = 0; i < sharkDirList.size(); i++) {
            int count = 0;
            int si = shark.i;
            int sj = shark.j;
            boolean isValid = true;
            boolean[][] visited = new boolean[4][4];
            for (int j = 0; j < sharkDirList.get(i).length; j++) {
                int d = sharkDirList.get(i)[j];
                si += sdx[d];
                sj += sdy[d];
                if (!isValid(si, sj)) {
                    count = 0;
                    isValid = false;
                    break;
                }
                if (visited[si][sj]) continue;
                visited[si][sj] = true;
                count += map[si][sj].size();
            }
            if (isValid && count > canEat) {
                canEat = count;
                choice = i;
            }
        }
        int si = shark.i;
        int sj = shark.j;
        boolean[][] visited = new boolean[4][4];
        for (int i = 0; i < sharkDirList.get(choice).length; i++) {
            int d = sharkDirList.get(choice)[i];
            si += sdx[d];
            sj += sdy[d];
            shark.i = si;
            shark.j = sj;
            if (visited[si][sj]) continue;
            visited[si][sj] = true;
            if (map[si][sj].size() != 0) {
                map[si][sj] = new ArrayList<>();
                scentMap.putIfAbsent(si + " " + sj, 0);
                scentQueue.add(new Scent(si, sj, simulCount));
                scentMap.put(si + " " + sj, scentMap.get(si + " " + sj) + 1);
            }
        }
    }

    static void disappearScent() {
        while (!scentQueue.isEmpty() && scentQueue.peek().time + 2 == simulCount) {
            Scent scent = scentQueue.poll();
            scentMap.put(scent.getKey(), scentMap.get(scent.getKey()) - 1);
            if (scentMap.get(scent.getKey()) == 0)
                scentMap.remove(scent.getKey());
        }
    }

    static void applyClone() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j].addAll(clonedFish[i][j]);
            }
        }
    }

    static void simulate() {
        initSharkDirList(0, new int[3]);
        while (S-- > 0) {
            cloneFish();
            moveFish();
            moveShark();
            disappearScent();
            applyClone();
            simulCount++;
        }
    }

    static int countAns() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                count += map[i][j].size();
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Fish f = new Fish(r - 1, c - 1, d - 1);
            map[r - 1][c - 1].add(f);
        }
        st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        shark = new Fish(r - 1, c - 1, -1);
        simulate();
        System.out.println(countAns());
    }
}
